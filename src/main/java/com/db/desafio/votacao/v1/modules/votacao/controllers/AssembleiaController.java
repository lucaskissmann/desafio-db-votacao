/**
 * Filename:    AssembleiaController.java
 *
 * Description: Implementation of the AssembleiaController class.
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.v1.config.ApplicationContext;
import com.db.desafio.votacao.v1.modules.Controller;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterAssembleiaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Assembleia;
import com.db.desafio.votacao.v1.modules.votacao.services.AssembleiaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = ApplicationContext.VERSION + "assembleias" )
public class AssembleiaController
    extends 
        Controller
{
    @Autowired
    private AssembleiaService assembleiaService;

    /**
     * getAssembleias
     * 
     * @return ResponseEntity<List<Assembleia>>
     */
    @GetMapping()
    public ResponseEntity<List<Assembleia>> getAssembleias()
    {
        return ok( assembleiaService.getAssembleias() );
    }

    /**
     * getAssembleia
     * 
     * @return ResponseEntity<Assembleia>
     */
    @GetMapping("{assembleiaId}")
    public ResponseEntity<Assembleia> getAssembleia( @PathVariable("assembleiaId") long assembleiaId )
    {
        return ok( assembleiaService.getAssembleiaById( assembleiaId ));
    }

    /**
     * createAssembleia
     * 
     * @param assembleiaDTO RegisterAssembleiaDTO
     * @return ResponseEntity<Assembleia>
     */
    @PostMapping()
    public ResponseEntity<Assembleia> createAssembleia( @RequestBody @Valid RegisterAssembleiaDTO assembleiaDTO )
    {
        return created( assembleiaService.createAssembleia( assembleiaDTO ));
    }
}
