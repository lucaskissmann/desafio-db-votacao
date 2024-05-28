/**
 * Filename:    Error.java
 *
 * Description: Implementation of the Error class.
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

package com.db.desafio.votacao.v1.helpers;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Error 
{
    private int code;
    private String status;
    private String message;

    /**
     * Error
     * 
     * @param httpStatus HttpStatus
     * @param message String
     */
    public Error( HttpStatus httpStatus, String message )
    {
        this.code = httpStatus.value();
        this.message = message;
        this.status = httpStatus.getReasonPhrase();    
    }
}