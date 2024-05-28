/**
 * Filename:    Handler.java
 *
 * Description: Implementation of the Handler interface.
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

package com.db.desafio.votacao.v1.helpers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.db.desafio.votacao.v1.helpers.Error;

@ControllerAdvice
public interface Handler<T extends Exception>
{
    /**
     * handle
     * 
     * @param e T
     * @return ResponseEntity<Error>
     */
    public ResponseEntity<Error> handle( T e );

    /**
     * response
     * 
     * @param message String
     * @param status HttpStatus
     * @return ResponseEntity<Error>
     */
    public default ResponseEntity<Error> response( String message, HttpStatus status )
    {
        return new ResponseEntity<Error>( new Error( status, message ), status );
    }
}
