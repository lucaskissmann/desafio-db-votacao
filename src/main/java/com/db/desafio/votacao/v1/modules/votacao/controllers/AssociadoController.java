/**
 * Filename:    AssociadoController.java
 *
 * Description: Implementation of the AssociadoController class.
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

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.modules.Controller;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterAssociadoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;
import com.db.desafio.votacao.v1.modules.votacao.data.swagger.AssociadoSwagger;
import com.db.desafio.votacao.v1.modules.votacao.services.AssociadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = ApplicationContext.VERSION + "associados" )
public class AssociadoController 
    extends 
        Controller
    implements
        AssociadoSwagger
{
    @Autowired
    private AssociadoService associadoService;

    /**
     * getAssociados
     * 
     * @return ResponseEntity<List<Associado>>
     */
    @Override
    @GetMapping()
    public ResponseEntity<List<Associado>> getAssociados() 
    {
        return ok( associadoService.getAssociados() );
    }

    /**
     * createAssociado
     * 
     * @param associadoDTO RegisterAssociadoDTO
     * @return ResponseEntity<Associado>
     */
    @Override
    @PostMapping()
    public ResponseEntity<Associado> createAssociado( @RequestBody @Valid RegisterAssociadoDTO associadoDTO ) 
    {
        return created( associadoService.createAssociado( associadoDTO ));
    }

    /**
     * getAssociadoByDocument
     * 
     * @param document String
     * @return ResponseEntity<Associado>
     */
    @GetMapping("{document}")
    public ResponseEntity<Associado> getAssociadoByDocument( @PathVariable("document") String document )
    {
        return ok( associadoService.getAssociadoByDocument( document ));
    }
}
