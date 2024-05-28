/**
 * Filename:    PautaController.java
 *
 * Description: Implementation of the PautaController class.
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
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.PautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.services.PautaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = ApplicationContext.VERSION + "pautas" )
public class PautaController
    extends
        Controller
{
    @Autowired
    private PautaService pautaService;

    /**
     * getPautas
     * 
     * @return ResponseEntity<List<Pauta>>
     */
    @GetMapping()
    public ResponseEntity<List<Pauta>> getPautas()
    {
        List<Pauta> pautas = pautaService.getPautas();

        return ok( pautas );
    }

    /**
     * createPauta
     * 
     * @param pauta Pauta
     * @return ResponseEntity<Pauta>
     */
    @PostMapping()
    public ResponseEntity<Pauta> createPauta( @RequestBody @Valid RegisterPautaDTO pautaDTO )
    {
        return created( pautaService.createPauta( pautaDTO ));
    }

    /**
     * getPautaResult
     * 
     * @param pautaId long
     * @return ResponseEntity<PautaResultDTO>
     */
    @GetMapping("{pautaId}")
    public ResponseEntity<PautaDTO> getPautaResult( @PathVariable("pautaId") long pautaId )
    {
        return ok( pautaService.getPautaResult( pautaId ));
    }
}