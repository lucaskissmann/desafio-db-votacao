/**
 * Filename:    PautaRepository.java
 *
 * Description: Implementation of the PautaRepository class.
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

package com.db.desafio.votacao.v1.modules.votacao.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;

public interface PautaRepository 
    extends
        JpaRepository<Pauta, Long> 
{
    
}