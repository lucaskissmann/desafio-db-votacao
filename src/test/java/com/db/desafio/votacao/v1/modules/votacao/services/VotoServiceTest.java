
package com.db.desafio.votacao.v1.modules.votacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.db.desafio.votacao.v1.helpers.exceptions.BadRequestException;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.AssociadoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.VotoRepository;
import com.db.desafio.votacao.v1.modules.votacao.models.VotoStub;

@DisplayName("Voto service")
public class VotoServiceTest 
{
    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
	private AssociadoService associadoService;

	@Mock
	private PautaService pautaService;

    @Mock
    private Pauta pauta;

    private Voto mockVotoWithId = VotoStub.createVotoWithId();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( votoRepository.save( any( Voto.class ))).thenReturn( mockVotoWithId );
        when( votoRepository.findById( any( Long.class ))).thenReturn( Optional.of( mockVotoWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao registrar voto")
    public void Should_ReturnOk_CreatePauta()
    {
        Voto voto = votoService.addVoto( mockVotoWithId );
        
        assertEquals( mockVotoWithId, voto );
        verify( votoRepository, times( 1 )).save( any( Voto.class ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao registrar voto e associado não pode votar")
    public void Should_ReturnBadRequest_CreateVoto_AssociadoUnableToVote()
    {
        mockVotoWithId.getAssociado().setStatus( AssociadoEnum.UNABLE_TO_VOTE );
        BadRequestException ex = assertThrows( BadRequestException.class, () -> votoService.addVoto( mockVotoWithId ));
        assertEquals( "Associado de documento '" + mockVotoWithId.getAssociado().getDocument() + "' não está apto para votar", ex.getMessage() );
        
        verify( pautaService, times( 0 )).getPautaById( any( Long.class ));
        verify( associadoService, times( 0 )).getAssociadoByDocument( any( String.class ));
        verify( votoRepository, times( 0 )).save( any( Voto.class ));
    }
    
    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao registrar voto e associado já votou")
    public void Should_ReturnBadRequest_CreateVoto_AssociadoAlreadyVote()
    {
        when( votoRepository.existsByAssociadoIdAndPautaId( any( Long.class ), any( Long.class ))).thenReturn( true );

        BadRequestException ex = assertThrows( BadRequestException.class, () -> votoService.addVoto( mockVotoWithId ));
        assertEquals( "Associado de documento '" + mockVotoWithId.getAssociado().getDocument() + "' já votou nesta pauta", ex.getMessage() );
        
        verify( pautaService, times( 0 )).getPautaById( any( Long.class ));
        verify( associadoService, times( 0 )).getAssociadoByDocument( any( String.class ));
        verify( votoRepository, times( 0 )).save( any( Voto.class ));
        verify(votoRepository, times(1)).existsByAssociadoIdAndPautaId( any( Long.class ), any( Long.class ));
    }
}
