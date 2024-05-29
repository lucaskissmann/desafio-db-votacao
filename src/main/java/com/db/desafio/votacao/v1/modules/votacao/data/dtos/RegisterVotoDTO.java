/**
 * Filename:    RegisterVotoDTO.java
 *
 * Description: Implementation of the RegisterVotoDTO class.
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
import com.db.desafio.votacao.v1.modules.votacao.data.enums.VotoEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterVotoDTO 
{
    @NotNull( message = "O documento do associado é obrigatório." )
    String document;
    
    @NotNull( message = "O id da pauta é obrigatório." )
    Long pautaId;
    
    @CpfOrCnpj()
    @NotNull( message = "O voto é obrigatório ('Sim', 'Não', 'Branco', 'Abstenção')" )
    VotoEnum voto;
}
