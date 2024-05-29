/**
 * Filename:    Pauta.java
 *
 * Description: Implementation of the Pauta class.
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.v1.config.ApplicationContext;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.PautaEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.VotoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table( name = "pautas" )
public class Pauta 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Builder.Default
    private String description = "";

    @OneToMany( cascade = CascadeType.ALL )
	@JoinTable(
        name = "pautas_votacoes", 
        joinColumns = {
		    @JoinColumn(
                name = "ref_pauta",
                referencedColumnName = "id"
            )
        },
        inverseJoinColumns = {
		    @JoinColumn(
                name = "ref_votos",
                referencedColumnName = "id"
            )
        }
    )
    @Builder.Default
	private List<Voto> votos = new ArrayList<Voto>();

    private LocalDateTime startDate;
	private LocalDateTime endDate;

    @Transient
    @Enumerated( EnumType.STRING )
    private PautaEnum state;

    /**
     * getState
     * 
     * @return PautaEnum
     */
    public PautaEnum getState()
    {
        updateState();

        return this.state;
    }

    /**
     * updateState
     */
    public void updateState()
    {
        if( isVotingPeriodActive() )
        {
            this.state = PautaEnum.AGUARDANDO_VOTACAO;
        }
        else 
        {
            updateStateBasedOnVotes();
        }
    }

    /**
     * isVotingPeriodActive
     * 
     * @return boolean
     */
    private boolean isVotingPeriodActive() 
    {
        return endDate.isAfter( ApplicationContext.now() );
    }

    /**
     * updateStateBasedOnVotes
     * 
     */
    private void updateStateBasedOnVotes() 
    {
        long approvedVotes = votos.stream().filter( voto -> voto.getVoto().equals( VotoEnum.SIM )).count();
        long reprovedVotes = votos.stream().filter( voto -> voto.getVoto().equals( VotoEnum.NAO )).count();

        if( approvedVotes == reprovedVotes ) 
        {
            this.state = PautaEnum.EMPATADA;

        } 
        else if( approvedVotes > reprovedVotes ) 
        {
            this.state = PautaEnum.APROVADA;
        }
        else if( approvedVotes == 0 && reprovedVotes == 0 )
        {
            this.state = PautaEnum.ANULADA;
        }
        else 
        {
            this.state = PautaEnum.REPROVADA;
        }
    }

    /**
     * addVoto
     * 
     * @param voto Voto
     */
    public void addVoto( Voto voto )
    {
        this.votos.add( voto );
    }
}