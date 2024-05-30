/**
 * Filename:    PautaEnum.java
 *
 * Description: Implementation of the PautaEnum class.
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

public enum PautaEnum 
{
    APROVADA( "Aprovada" ),
    REPROVADA( "Reprovada" ),
    EMPATADA( "Empatada" ),
    ANULADA( "Anulada" ),
    AGUARDANDO_VOTACAO( "Aguardando votação" );

    private final String status;

    /**
     * PautaEnum
     * 
     * @param voto String
     */
    PautaEnum( String voto ) 
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
