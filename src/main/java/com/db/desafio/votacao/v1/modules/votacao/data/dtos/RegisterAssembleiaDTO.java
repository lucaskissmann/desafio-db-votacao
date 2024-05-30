/**
 * Filename:    RegisterAssembleiaDTO.java
 *
 * Description: Implementation of the RegisterAssembleiaDTO class.
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

package com.db.desafio.votacao.v1.modules.votacao.data.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.v1.config.ApplicationContext;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterAssembleiaDTO
{
    @NotNull( message = "O nome da assembleia é obrigatório" )
	String name;

	@Builder.Default
	private String description = "";

	@Builder.Default
	@JsonIgnore
	private List<Pauta> pautas = new ArrayList<>();

	@Builder.Default
	private LocalDate createdAt = ApplicationContext.today();

	@Builder.Default
	private LocalDate startDate = ApplicationContext.today();
	
    @Builder.Default
	private LocalDate endDate = ApplicationContext.today().plusDays( 1 );
}