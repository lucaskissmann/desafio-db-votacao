/**
 * Filename:    AssociadoEnum.java
 *
 * Description: Implementation of the AssociadoEnum class.
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

public enum AssociadoEnum 
{
    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private final String value;

    /**
     * AssociadoEnum
     * 
     * @param status String
     */
    AssociadoEnum( String status ) 
    {
        this.value = status;
    }

    /**
     * getValue
     * 
     * @return
     */
    @JsonValue
    public String getValue() 
    {
        return value;
    }
}