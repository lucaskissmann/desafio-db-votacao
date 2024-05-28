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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.PautaRepository;


@Service
public class PautaService 
{
    @Autowired
    private PautaRepository pautaRepository;

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
                            
        return this.addPauta( pauta );
    }

    /**
     * addPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta addPauta( Pauta pauta )
    {
        return this.pautaRepository.save( pauta );
    }

    /**
     * updatePauta
     * 
     * @param pauta Pauta
     */
    public void updatePauta( Pauta pauta )
    {
        this.pautaRepository.save( pauta );
    }
}