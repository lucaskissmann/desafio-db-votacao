/**
 * Filename:    AssembleiaService.java
 *
 * Description: Implementation of the AssembleiaService class.
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
import org.springframework.stereotype.Service;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.helpers.exceptions.BadRequestException;
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterAssembleiaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Assembleia;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.AssembleiaRepository;

@Service
public class AssembleiaService 
{
    
    @Autowired
    private AssembleiaRepository assembleiaRepository;
    
    /**
     * getAssembleias
     * 
     * @return List<Assembleia>
     */
    public List<Assembleia> getAssembleias()
    {
        return assembleiaRepository.findAll();
    } 
    
    /**
     * getAssembleiaById
     * 
     * @param assembleiaId long
     * @return Assembleia
     */
    public Assembleia getAssembleiaById( long assembleiaId )
    {
        return assembleiaRepository.findById( assembleiaId )
                                    .orElseThrow( () -> new NotFoundException( "Assembleia não localizada para o id: #" + assembleiaId ));
    }

    /**
     * updateAssembleia
     * 
     * @param assembleia Assembleia 
     * @return Assembleia
     */
    public void updateAssembleia( Assembleia assembleia )
    {
        this.validateAssembleiaDates( assembleia );
        assembleiaRepository.save( assembleia );
    }

    /**
     * createAssembleia
     * 
     * @param dto RegisterAssembleiaDTO
     * @return Assembleia
     */
    public Assembleia createAssembleia( RegisterAssembleiaDTO dto )
    {
        Assembleia assembleia = Assembleia.builder()
                                        .name( dto.getName() )
                                        .description( dto.getDescription() )
                                        .createdAt( dto.getCreatedAt() )
                                        .startDate( dto.getStartDate() )
                                        .endDate( dto.getEndDate() )
                                        .build();

        return addAssembleia( assembleia );
    }

    /**
     * addAssembleia
     * 
     * @param assembleia Assembleia 
     * @return Assembleia
     */
    public Assembleia addAssembleia( Assembleia assembleia )
    {
        this.validateAssembleiaDates( assembleia );
        return assembleiaRepository.save( assembleia );
    }

    /**
     * isValidDates
     * 
     * @param assembleia Assembleia
     * @param pauta Pauta
     * @return boolean
     */
    public boolean isValidDates( Assembleia assembleia, Pauta pauta )
    {
        return pauta.getStartDate().toLocalDate().isBefore( assembleia.getStartDate() )
            || pauta.getEndDate().toLocalDate().isAfter( assembleia.getEndDate() );
    }

    /**
     * validateAssembleiaDates
     * 
     * @param assembleia Assembleia
     */
    public void validateAssembleiaDates( Assembleia assembleia )
    {
        if( assembleia.getStartDate().isBefore( ApplicationContext.today() ))
            throw new BadRequestException("A assembleia '" + assembleia.getName() + "' não pode ter data inicial anterior a data atual.");
        
        if( assembleia.getEndDate().isBefore( assembleia.getStartDate() ))
            throw new BadRequestException("Data final da assembleia deve ser posterior a data inicial.");
    }

}
