package com.db.desafio.votacao.v1.repositories;

import com.db.desafio.votacao.v1.models.Voto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository 
    extends
        JpaRepository<Voto, Long> 
{
    
}
