/**
 * Filename:    ValidatorSwagger.java
 *
 * Description: Implementation of the ValidatorSwagger class.
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

package com.db.desafio.votacao.v1.modules.validarDocumentos.data.swagger;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.db.desafio.votacao.v1.modules.validarDocumentos.data.models.Validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ValidatorSwagger
{
    final String TAG_NAME = "Validação de documentos";

    @Operation(
        operationId = "validar documentos",
        summary = "Verifica se o documento informado é válido.",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Documento está válido.",
                content = @Content( 
                            schema = @Schema( implementation = Validator.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "CPF ou CNPJ inválido",
                content = @Content( 
                            schema = @Schema( implementation = Validator.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Validator> getValidator( @PathVariable("cpf") String cpfOrCnpj );
}