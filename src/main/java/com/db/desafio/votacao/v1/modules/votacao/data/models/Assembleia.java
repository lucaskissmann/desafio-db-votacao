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

package com.db.desafio.votacao.v1.modules.votacao.data.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.context.ApplicationContext;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table( name = "assembleias" )
public class Assembleia 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;   
    
    private String name;

    @Builder.Default
    private String description = "";

    @Builder.Default
    @Column( name = "created_at", nullable = false)
    private LocalDate createdAt = ApplicationContext.today();

    @Column( name = "start_date", nullable = false )
    private LocalDate startDate;

    @Column( name = "end_date", nullable = false )
    private LocalDate endDate;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable(
        name = "assembleias_pautas", 
        joinColumns = {
            @JoinColumn(
                name = "ref_assembleia", 
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = { 
            @JoinColumn(
                name = "ref_pauta",
                referencedColumnName = "id"
            )
        }
    )
    @Builder.Default
    List<Pauta> pautas = new ArrayList<>();

    /**
     * addPauta
     * 
     * @param pauta Pauta
     */
    public void addPauta( Pauta pauta )
    {
        this.pautas.add( pauta );
    }

}
