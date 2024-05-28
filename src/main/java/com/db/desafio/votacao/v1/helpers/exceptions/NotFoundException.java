/**
 * Filename:    NotFoundException.java
 *
 * Description: Implementation of the NotFoundException class.
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

public class NotFoundException 
    extends
        RuntimeException
{
    /**
     * NotFoundException
     * 
     * @param message String
     */
    public NotFoundException( String message )
    {
        super( message );
    }

    /**
     * NotFoundException
     * 
     * @param cause Throwable
     */
    public NotFoundException( Throwable cause )
    {
        super( cause );
    }

    /**
     * NotFoundException
     * 
     * @param message String
     * @param cause Throwable
     */
    public NotFoundException( String message, Throwable cause )
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
        return HttpStatus.NOT_FOUND;
    }
}