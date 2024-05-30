
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
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.PautaEnum;
import com.db.desafio.votacao.v1.modules.votacao.models.PautaStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Pauta controller")
public class PautaControllerTest 
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/pautas";

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssembleia ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar Ok ao criar uma Pauta")
    public void Should_ReturnOk_CreatePauta() throws Exception
    {
        final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockPautaDTO.getName() ))
				.andExpect( jsonPath("$.description").value( mockPautaDTO.getDescription() ))
				.andExpect( jsonPath("$.votos").value( mockPautaDTO.getVotos() ))
				.andExpect( jsonPath("$.startDate").value( mockPautaDTO.getStartDate().toString() ))
				.andExpect( jsonPath("$.endDate").value( mockPautaDTO.getEndDate().toString() ))
				.andExpect( jsonPath("$.status").value( PautaEnum.AGUARDANDO_VOTACAO.getValue() ));
    }

	@Test
	@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.resetDB )
	@DisplayName("[POST] Deve retornar NotFound ao criar uma Pauta e não existir Assembleia")
	public void Should_ReturnNotFound_CreatePauta() throws Exception
	{
		final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Assembleia não localizada para o id: #" + mockPautaDTO.getAssembleiaId() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssembleia ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[POST] Deve retornar BadRequest ao criar uma Pauta com datas inválidas")
	public void Should_ReturnBadRequest_CreatePautaWithWrongDates() throws Exception
	{
		final RegisterPautaDTO mockPautaDTO = PautaStub.createPautaDTOWithWrongDates();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockPautaDTO )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Data inicial e final da Pauta devem estar dentro do escopo de datas da Assembleia" ));
	}

	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoSim ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta aprovada")
	public void Should_ReturnOk_GetPautaResult_Approved() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 0 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( PautaEnum.APROVADA.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoNao ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta reprovada")
	public void Should_ReturnOk_GetPautaResult_Rejected() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 0 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( PautaEnum.REPROVADA.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertFinishedPauta ),
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertAssociadoAbleToVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoSim ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = QueryProvider.insertVotoNao ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = QueryProvider.resetDB ),
    })
	@DisplayName("[GET] Deve retornar Ok ao buscar o resultado da Pauta empatada")
	public void Should_ReturnOk_GetPautaResult_Tied() throws Exception
	{
		long pautaId = 1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.pautaId").value( pautaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 2 ))
				.andExpect( jsonPath("$.status").value( PautaEnum.EMPATADA.getValue() ));
	}

	@Test
	@DisplayName("[POST] Deve retornar NotFound ao buscar o resultado da Pauta que não existe")
	public void Should_ReturnNotFound_GetPautaResult() throws Exception
	{
		long pautaId = -1;

		mockMvc.perform( get( PATH + "/{pautaId}", pautaId ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Pauta não localizada para o id: #" + pautaId ));
	}
}
