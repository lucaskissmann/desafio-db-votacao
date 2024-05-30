/**
 * Filename:    VotoController.java
 *
 * Description: Implementation of the VotoController class.
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

package com.db.desafio.votacao.v1.modules.votacao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.modules.Controller;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterVotoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;
import com.db.desafio.votacao.v1.modules.votacao.data.swagger.VotoSwagger;
import com.db.desafio.votacao.v1.modules.votacao.services.VotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = ApplicationContext.VERSION + "votos" )
public class VotoController
    extends
        Controller
    implements
        VotoSwagger
{
    @Autowired
    private VotoService votoService;

    /**
     * getVotos
     * 
     * @return List<Voto>
     */
    @Override
    @GetMapping()
    public ResponseEntity<List<Voto>> getVotos() 
    {
        return ok( votoService.getVotos() );
    }

    /**
     * createVoto
     * 
     * @param votoDTO RegisterVotoDTO
     * @return
     */
    @Override
    @PostMapping()
    public ResponseEntity<Voto> createVoto( @RequestBody @Valid RegisterVotoDTO votoDTO ) 
    {    
        return created( votoService.createVoto( votoDTO ));
    }
}