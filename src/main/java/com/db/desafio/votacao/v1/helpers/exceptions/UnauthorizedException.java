/**
 * Filename:    UnauthorizedException.java
 *
 * Description: Implementation of the UnauthorizedException class.
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

public class UnauthorizedException 
    extends 
        RuntimeException
{
    /**
     * UnauthorizedException
     * 
     * @param message String
     */
    public UnauthorizedException( String message )
    {
        super( message );
    }

    /**
     * UnauthorizedException
     * 
     * @param cause Throwable
     */
    public UnauthorizedException( Throwable cause )
    {
        super( cause );
    }

    /**
     * UnauthorizedException
     * 
     * @param message String
     * @param cause Throwable
     */
    public UnauthorizedException( String message, Throwable cause )
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
        return HttpStatus.UNAUTHORIZED;
    }
}
