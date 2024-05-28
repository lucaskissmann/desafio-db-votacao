/**
 * Filename:    DuplicateObjectException.java
 *
 * Description: Implementation of the DuplicateObjectException class.
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

package com.db.desafio.votacao.v1.helpers.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateObjectException
    extends
        RuntimeException
{
    /**
     * DuplicateObjectException
     * 
     * @param message String
     */
    public DuplicateObjectException( String message )
    {
        super( message );
    }

    /**
     * DuplicateObjectException
     * 
     * @param cause Throwable
     */
    public DuplicateObjectException( Throwable cause )
    {
        super( cause );
    }

    /**
     * DuplicateObjectException
     * 
     * @param message String
     * @param cause Throwable
     */
    public DuplicateObjectException( String message, Throwable cause )
    {
        super( message, cause );
    }

    /**
     * getStatus
     * 
     * @return getStatus
     */
    public HttpStatus getStatus()
    {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}