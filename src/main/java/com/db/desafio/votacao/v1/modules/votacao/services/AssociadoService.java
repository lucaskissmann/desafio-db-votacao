/**
 * Filename:    AssociadoService.java
 *
 * Description: Implementation of the AssociadoService class.
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

package com.db.desafio.votacao.v1.modules.votacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.helpers.exceptions.DuplicateObjectException;
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterAssociadoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.AssociadoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.AssociadoRepository;

@Service
public class AssociadoService 
{
    @Autowired
    private AssociadoRepository associadoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * getAssociados
     * 
     * @return List<Associado>
     */
    public List<Associado> getAssociados()
    {
        return associadoRepository.findAll();
    }
    
    /**
     * getAssociadoByDocument
     * 
     * @param document String
     * @return Associado
     */
    public Associado getAssociadoByDocument( String document )
    {
        return associadoRepository.findByDocument( document )
                                    .orElseThrow( () -> new NotFoundException( "Associado não localizado para o documento: " + document ));
    }  
    
    /**
     * createAssociado
     * 
     * @param dto RegisterAssociadoDTO
     * @return Associado
     */
    public Associado createAssociado( RegisterAssociadoDTO dto )
    {
        Associado associado = Associado.builder()
                                        .name( dto.getName() )
                                        .document( dto.getDocument() )
                                        .build();

        return addAssociado( associado );
    }

    /**
     * addAssociado
     * 
     * @param associado Associado
     * @return Associado
     */
    public Associado addAssociado( Associado associado )
    {
        if( associadoRepository.existsByDocument( associado.getDocument() ) )
        {
            throw new DuplicateObjectException( "Já existe um associado cadastrado com este documento: " + associado.getDocument() );
        }

        AssociadoEnum status = validateDocument( associado.getDocument() ) ? AssociadoEnum.ABLE_TO_VOTE : AssociadoEnum.UNABLE_TO_VOTE;
        associado.setStatus( status );

        return associadoRepository.save( associado );
    }

    /**
     * validateDocument
     * 
     * @param document String
     * @return boolean
     */
    public boolean validateDocument( String document )
    {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl( ApplicationContext.getServerBaseUrl() )
                                                                .path( "/validators/{cpfOrCnpj}" );

        String apiUrl = uriBuilder.buildAndExpand( document ).toUriString();

        try
        {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity( apiUrl, String.class);
    
            if( responseEntity.getStatusCode().is2xxSuccessful() )
            {
                return true;
            }
        }
        catch( RestClientException ex )
        {
            
        }

        return false;
    }
}
