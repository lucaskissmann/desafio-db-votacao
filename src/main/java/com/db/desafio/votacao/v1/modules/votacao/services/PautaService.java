/**
 * Filename:    PautaService.java
 *
 * Description: Implementation of the PautaService class.
 *
 * Revision:    1.0
 *
 * Changed by:  lk
 *
 * Author:      Lucas Kissmann
 * Email:       lucaskissmann@gmail.com
 *
 * Copyright (c) [2024] Lucas Kissmann
 * This program is part of the voting challenge by DB.
 * It is intellectual property and may only be used and/or copied with permission.
 * Challenge: https://github.com/dbserver/desafio-votacao
 * 
 */

package com.db.desafio.votacao.v1.modules.votacao.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.helpers.exceptions.BadRequestException;
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.PautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.VotoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Assembleia;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.PautaRepository;


@Service
public class PautaService 
{
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private AssembleiaService assembleiaService;

    /**
     * getPautas
     * 
     * @return List<Pauta>
     */
    public List<Pauta> getPautas()
    {
        return this.pautaRepository.findAll();    
    }

    /**
     * getPautaById
     * 
     * @param pautaId long
     * @return Pauta
     */
    public Pauta getPautaById( long pautaId )
    {
        return this.pautaRepository.findById( pautaId )
                                    .orElseThrow( () -> new NotFoundException( "Pauta não localizada para o id: #" + pautaId ));
    }

    /**
     * createPauta
     * 
     * @param dto RegisterPautaDTO
     * @return Pauta
     */
    public Pauta createPauta( RegisterPautaDTO dto )
    {
        Pauta pauta = Pauta.builder()
                            .name( dto.getName() )
                            .description( dto.getDescription() )
                            .votos( dto.getVotos() )
                            .startDate( dto.getStartDate() )
                            .endDate( dto.getEndDate() )
                            .build();

        if( assembleiaService.isValidDates( assembleiaService.getAssembleiaById( dto.getAssembleiaId() ), pauta ))
            throw new BadRequestException("Data inicial e final da Pauta devem estar dentro do escopo de datas da Assembleia" );
                            
        this.addPauta( pauta );

        Assembleia assembleia = assembleiaService.getAssembleiaById( dto.getAssembleiaId() );

        assembleia.addPauta( pauta );
        assembleiaService.updateAssembleia( assembleia );

        return pauta;
    }

    /**
     * addPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta addPauta( Pauta pauta )
    {
        this.validatePautaDate( pauta );
        return this.pautaRepository.save( pauta );
    }

    /**
     * updatePauta
     * 
     * @param pauta Pauta
     */
    public void updatePauta( Pauta pauta )
    {
        this.validatePautaDate( pauta );
        this.pautaRepository.save( pauta );
    }

    /**
     * getPautaResult
     * 
     * @param pautaId long
     * @return PautaDTO
     */
    public PautaDTO getPautaResult( long pautaId )
    {
        Pauta pauta = this.getPautaById( pautaId ); 

        Map<VotoEnum, Long> voteCount = Arrays.stream( VotoEnum.values() )
                                            .collect( Collectors.toMap( (voto) -> voto, 
                                                                        (voto) -> pauta.getVotos()
                                                                                    .stream()
                                                                                    .filter( (v) -> 
                                                                                            v.getVoto() == voto )
                                                                                    .count() ));

        return PautaDTO.builder()
                .pautaId( pauta.getId() )
                .status( pauta.getStatus() )
                .total( pauta.getVotos().size() )
                .approved( voteCount.get( VotoEnum.SIM ))
                .rejected( voteCount.get( VotoEnum.NAO ))
                .abstention( voteCount.get( VotoEnum.ABSTENCAO ))
                .protest( voteCount.get( VotoEnum.BRANCO ))
                .build();
    }

    /**
     * validatePautaDate
     * 
     * @param pauta Pauta
     */
    public void validatePautaDate( Pauta pauta )
    {
        if( pauta.getStartDate().toLocalDate().isBefore( ApplicationContext.today()))
            throw new BadRequestException("A pauta '" + pauta.getName() + "' não pode ter data inicial anterior a data atual.");
        
        if( pauta.getEndDate().isBefore( pauta.getStartDate() ))
            throw new BadRequestException("Horário final da pauta deve ser posterior ao horário inicial.");
    }
}