/**
 * Filename:    SwaggerConfig.java
 *
 * Description: Implementation of the SwaggerConfig class.
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

package com.db.desafio.votacao.v1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig 
{
    @Bean
    OpenAPI apiInfo() 
    {
        return new OpenAPI()
                .info( new Info()
                        .title( "Desafio votação - DB" )
                        .description("Projeto backend para gerenciar sessões de votação.")
                        .contact( new Contact()
                                    .name( "Lucas Kissmann" )
                                    .url( "http://github.com/lucaskissmann" ))
                                    .version("1.0")
                                    .license( new License() )
                                    .termsOfService("") );
    }
}