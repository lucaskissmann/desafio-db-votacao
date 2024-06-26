/**
 * Filename:    ApplicationContext.java
 *
 * Description: Implementation of the ApplicationContext class.
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

package com.db.desafio.votacao.context;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ApplicationContext 
{
    public static final String VERSION = "v1/";

    /**
     * getServerBaseUrl
     * 
     * @return String
     */
    public static String getServerBaseUrl() 
    {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + VERSION;
    }

    /**
     * today
     * 
     * @return LocalDate
     */
    public static LocalDate today()
    {
        return LocalDate.now();
    }

    /**
     * now
     * 
     * @return LocalDateTime
     */
    public static LocalDateTime now()
    {
        return LocalDateTime.now().withNano( 0 );
    }
}