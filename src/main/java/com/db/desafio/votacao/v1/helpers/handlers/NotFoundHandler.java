/**
 * Filename:    NotFoundHandler.java
 *
 * Description: Implementation of the NotFoundHandler class.
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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.desafio.votacao.v1.helpers.Error;
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;

@ControllerAdvice
public class NotFoundHandler
    implements 
        Handler<NotFoundException>
{
    /**
     * handle
     * 
     * @param e NotFoundException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Error> handle( NotFoundException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}
