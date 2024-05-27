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

package com.db.desafio.votacao.v1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.v1.models.Pauta;
import com.db.desafio.votacao.v1.repositories.PautaRepository;


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
     * createPauta
     * 
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta createPauta( Pauta pauta )
    {
        return this.pautaRepository.save( pauta );
    }
}