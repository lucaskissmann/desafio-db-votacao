/**
 * Filename:    VotoEnum.java
 *
 * Description: Implementation of the VotoEnum class.
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

package com.db.desafio.votacao.v1.modules.votacao.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VotoEnum 
{
    SIM("Sim"),
    NAO("Não"),
    ABSTENCAO("Abstenção"),
    BRANCO("Branco");

    private final String status;

    /**
     * VotoEnum
     * 
     * @param voto String
     */
    VotoEnum( String voto ) 
    {
        this.status = voto;
    }

    /**
     * getValue
     * 
     * @return
     */
    @JsonValue
    public String getValue() 
    {
        return status;
    }
}