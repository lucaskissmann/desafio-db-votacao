/**
 * Filename:    RegisterAssociadoDTO.java
 *
 * Description: Implementation of the RegisterAssociadoDTO class.
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

import com.db.desafio.votacao.v1.helpers.validation.CpfOrCnpj;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterAssociadoDTO 
{
    @NotNull( message = "O nome do associado é obrigatório." )
	private String name;

    @CpfOrCnpj()
    @NotNull( message = "O documento (CPF ou CNPJ) do associado é obrigatório." )
    private String document;
}