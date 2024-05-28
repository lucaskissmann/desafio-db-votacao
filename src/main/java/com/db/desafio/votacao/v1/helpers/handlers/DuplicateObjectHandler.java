/**
 * Filename:    DuplicateObjectHandler.java
 *
 * Description: Implementation of the DuplicateObjectHandler class.
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
import com.db.desafio.votacao.v1.helpers.exceptions.DuplicateObjectException;

@ControllerAdvice
public class DuplicateObjectHandler 
    implements 
        Handler<DuplicateObjectException>
{
    /**
     * handle
     * 
     * @param e DuplicateObjectException
     * @return ResponseEntity<Error>
     */
    @Override
    @ExceptionHandler( DuplicateObjectException.class )
    public ResponseEntity<Error> handle( DuplicateObjectException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}