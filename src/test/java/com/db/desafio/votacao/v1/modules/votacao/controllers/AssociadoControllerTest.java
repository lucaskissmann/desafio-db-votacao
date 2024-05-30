
package com.db.desafio.votacao.v1.modules.votacao.controllers;

import static com.db.desafio.votacao.v1.helpers.Serializer.json;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.db.desafio.votacao.v1.helpers.QueryProvider;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;
import com.db.desafio.votacao.v1.modules.votacao.models.AssociadoStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Associado controller")
public class AssociadoControllerTest 
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/associados";

    @Test
	@DisplayName("[POST] Deve retornar Ok ao criar um Associado")
    public void Should_ReturnOk_CreateAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociado )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociado.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociado.getDocument() ));
    }

    @Test
    	@SqlGroup({
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
		@Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB )
	})
	@DisplayName("[POST] Deve retornar UnprocessableEntity ao criar um Associado com documento que já existe")
    public void Should_ReturnUnprocessableEntity_CreateAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithoutId();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociado )))
				.andExpect( status().isUnprocessableEntity() )
				.andExpect( jsonPath("$.code").value( 422 ))
				.andExpect( jsonPath("$.status").value( "Unprocessable Entity" ))
				.andExpect( jsonPath("$.message").value( "Já existe um associado cadastrado com este documento: " + mockAssociado.getDocument() ));
    }

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB )
	})
    @DisplayName("[GET] Deve retornar Ok ao buscar um associado")
    public void Should_ReturnOk_GetAssociado() throws Exception
    {
        final Associado mockAssociado = AssociadoStub.createAssociadoWithId();

		mockMvc.perform( get( PATH + "/{document}", mockAssociado.getDocument() ))
                
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociado.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociado.getDocument() ))
				.andExpect( jsonPath("$.status").value( mockAssociado.getStatus().getValue() ));
    }
    
    @Test
	@DisplayName("[POST] Deve retornar NotFound ao buscar um Associado que não existe")
    public void Should_ReturnNotFound_GetAssociado() throws Exception
    {
        String associadoDocument = "65977371055";

        mockMvc.perform( get( PATH + "/{document}", associadoDocument ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Associado não localizado para o documento: " + associadoDocument ));
    }
}
