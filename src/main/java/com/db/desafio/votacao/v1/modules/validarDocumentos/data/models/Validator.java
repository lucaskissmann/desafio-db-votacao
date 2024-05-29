/**
 * Filename:    Validator.java
 *
 * Description: Implementation of the Validator class.
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

package com.db.desafio.votacao.v1.modules.validarDocumentos.data.models;

import com.db.desafio.votacao.v1.modules.validarDocumentos.data.enums.ValidatorEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Validator 
{
    @Builder.Default
    ValidatorEnum state = ValidatorEnum.UNABLE_TO_VOTE;
}
