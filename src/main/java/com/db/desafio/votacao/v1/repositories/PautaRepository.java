package com.db.desafio.votacao.v1.repositories;

import com.db.desafio.votacao.v1.models.Pauta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository 
    extends
        JpaRepository<Pauta, Long> 
{
    
}