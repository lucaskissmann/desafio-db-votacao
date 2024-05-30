/**
 * Filename:    ValidatorController.java
 *
 * Description: Implementation of the ValidatorController class.
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

package com.db.desafio.votacao.v1.modules.validarDocumentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.Controller;
import com.db.desafio.votacao.v1.modules.validarDocumentos.data.enums.ValidatorEnum;
import com.db.desafio.votacao.v1.modules.validarDocumentos.data.models.Validator;
import com.db.desafio.votacao.v1.modules.validarDocumentos.data.swagger.ValidatorSwagger;
import com.db.desafio.votacao.v1.modules.validarDocumentos.services.ValidatorService;

@RestController
@RequestMapping( path = ApplicationContext.VERSION + "validators" )
public class ValidatorController 
    extends 
        Controller
    implements
        ValidatorSwagger
{
    @Autowired
    private ValidatorService validatorService;

    /**
     * getValidator
     * 
     * @param cpfOrCnpj String
     * @return ResponseEntity<Validator>
     */
    @Override
    @GetMapping("{cpfOrCnpj}")
    public ResponseEntity<Validator> getValidator( @PathVariable("cpfOrCnpj") String cpfOrCnpj )
    {
        try
        {
            Validator validator = validatorService.validateDocument( cpfOrCnpj );

            return ok( validator );
        }
        catch( NotFoundException notFoundException )
        {
            Validator validator = new Validator();
            validator.setStatus( ValidatorEnum.UNABLE_TO_VOTE );

            return new ResponseEntity<Validator>( validator, HttpStatus.NOT_FOUND );
        }
    }
}