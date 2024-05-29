/**
 * Filename:    VotoService.java
 *
 * Description: Implementation of the VotoService class.
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.v1.config.ApplicationContext;
import com.db.desafio.votacao.v1.helpers.exceptions.BadRequestException;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterVotoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.AssociadoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.VotoRepository;

@Service
public class VotoService 
{
    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;

    /**
     * getVotos
     * 
     * @return List<Voto>
     */
    public List<Voto> getVotos()
    {
        return votoRepository.findAll();
    }

    /**
     * existsByAssociadoAndPauta
     * 
     * @param associado Associado
     * @param pauta Pauta
     * @return boolean
     */
    public boolean existsByAssociadoAndPauta( Associado associado, Pauta pauta )
    {
        return votoRepository.existsByAssociadoIdAndPautaId( associado.getId(), pauta.getId() );
    }

    /**
     * createVoto
     * 
     * @param dto RegisterVotoDTO
     * @return Voto
     */
    public Voto createVoto( RegisterVotoDTO dto )
    {
        Associado associado = associadoService.getAssociadoByDocument( dto.getDocument() );            
        Pauta pauta = pautaService.getPautaById( dto.getPautaId() );

        Voto voto = Voto.builder()
                        .associado( associado )
                        .pauta( pauta )
                        .voto( dto.getVoto() )
                        .build();

        return addVoto( voto );
    }

    /**
     * addVoto
     * 
     * @param voto Voto
     * @return Voto
     */
    public Voto addVoto( Voto voto )
    {
        this.canVote( voto );

        Pauta pauta = voto.getPauta();

        pauta.addVoto( voto );
        pautaService.updatePauta( pauta );

        return votoRepository.save( voto );
    }

    /**
     * canVote
     * 
     * @param voto Voto
     */
    public void canVote( Voto voto )
    {
        if( voto.getPauta().getEndDate().isBefore( ApplicationContext.now() ))
        {
            throw new BadRequestException("Pauta encerrada");
        }

        if( voto.getAssociado().getState() == AssociadoEnum.UNABLE_TO_VOTE )
        {
            throw new BadRequestException("Associado de documento '" + voto.getAssociado().getDocument() + "' não está apto para votar");
        }
        
        if( existsByAssociadoAndPauta( voto.getAssociado(), voto.getPauta() ))
        {
            throw new BadRequestException("Associado de documento '" + voto.getAssociado().getDocument() + "' já votou nesta pauta");
        }
    }
}
