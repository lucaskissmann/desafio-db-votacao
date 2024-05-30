
package com.db.desafio.votacao.v1.modules.votacao.controllers;

import static com.db.desafio.votacao.v1.helpers.Serializer.json;

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
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterVotoDTO;
import com.db.desafio.votacao.v1.modules.votacao.models.VotoStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Voto controller")
public class VotoControllerTest 
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/votos";

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar Ok ao registrar um Voto")
    public void Should_ReturnOk_CreateVoto() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.voto").value( mockVoto.getVoto().getValue() ));
    }

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoUnableToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar BadRequest ao registrar um Voto com associado que não pode votar")
    public void Should_ReturnBadRequest_CreateVoto_AssociadoUnableToVote() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Associado de documento '" + mockVoto.getDocument() + "' não está apto para votar" ));
    }
    
    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoUnableToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar BadRequest ao registrar um Voto com pauta expirada")
    public void Should_ReturnBadRequest_CreateVoto_WithFinishedPauta() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Pauta encerrada" ));
    }
    
    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoSim ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar BadRequest ao registrar um Voto que associado já votou")
    public void Should_ReturnBadRequest_CreateVoto_AssociadoAlreadyVoted() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Associado de documento '" + mockVoto.getDocument() + "' já votou nesta pauta" ));
    }
    
    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar NotFound ao registrar um Voto e pauta não existir")
    public void Should_ReturnNotFound_CreateVoto_PautaNotExists() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Pauta não localizada para o id: #" + mockVoto.getPautaId() ));
    }
    
    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar NotFound ao registrar um Voto e associado não existir")
    public void Should_ReturnNotFound_CreateVoto_AssociadoNotExists() throws Exception
    {
        RegisterVotoDTO mockVoto = VotoStub.createRegisterVoto();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockVoto )))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Associado não localizado para o documento: " + mockVoto.getDocument() ));
    }
}
