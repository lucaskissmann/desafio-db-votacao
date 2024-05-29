/**
 * Filename:    ValidatorService.java
 *
 * Description: Implementation of the ValidatorService class.
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

package com.db.desafio.votacao.v1.modules.validarDocumentos.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.validarDocumentos.data.enums.ValidatorEnum;
import com.db.desafio.votacao.v1.modules.validarDocumentos.data.models.Validator;

@Service
public class ValidatorService 
{
    /**
     * validateDocument
     * 
     * @param document String
     * @return Validator
     */
    public Validator validateDocument( String document )
    {
        if( new Random().nextBoolean() )
        {
            Validator validator = new Validator();
            
            validator.setState( ValidatorEnum.ABLE_TO_VOTE );

            return validator;
        }
        
        throw new NotFoundException("Documento inválido.");
    }
}
