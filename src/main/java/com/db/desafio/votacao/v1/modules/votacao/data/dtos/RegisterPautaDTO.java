/**
 * Filename:    RegisterPautaDTO.java
 *
 * Description: Implementation of the RegisterPautaDTO class.
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.PautaEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;
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
public class RegisterPautaDTO
{
	@NotNull( message = "O ID da assembleia é obrigatório." )
	private long assembleiaId;

	@NotNull( message = "O nome da pauta é obrigatório." )
	private String name;

	@Builder.Default
	private String description = "";

	@JsonIgnore
	@Builder.Default
	private List<Voto> votos = new ArrayList<>();

	@Builder.Default
	private LocalDateTime startDate = ApplicationContext.now();

	@Builder.Default
	private LocalDateTime endDate = ApplicationContext.now().plusMinutes( 1 );

	@JsonIgnore
	@Builder.Default
	private PautaEnum status = PautaEnum.AGUARDANDO_VOTACAO;
}