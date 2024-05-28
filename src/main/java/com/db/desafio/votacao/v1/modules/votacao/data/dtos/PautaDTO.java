/**
 * Filename:    PautaDTO.java
 *
 * Description: Implementation of the PautaDTO class.
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

import com.db.desafio.votacao.v1.modules.votacao.data.enums.PautaEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaDTO 
{
    long pautaId;

    @Builder.Default
    String description = "";

    long approved;
    long rejected;
    long abstention;
    long protest;
    
    long total;

    PautaEnum status;
}
