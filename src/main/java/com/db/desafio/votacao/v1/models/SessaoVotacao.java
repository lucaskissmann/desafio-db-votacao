/**
 * Filename:    SessaoVotacao.java
 *
 * Description: Implementation of the SessaoVotacao class.
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

package com.db.desafio.votacao.v1.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "sessoes" )
public class SessaoVotacao 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;    

    @ManyToOne
    @JoinColumn( name = "ref_pauta" )
    private Pauta pauta;

    @Column( name = "start_date", nullable = false )
    private LocalDateTime startDate;

    @Column( name = "end_date", nullable = false )
    private LocalDateTime endDate;
}
