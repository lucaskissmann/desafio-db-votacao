
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
import com.db.desafio.votacao.v1.helpers.exceptions.NotFoundException;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.PautaRepository;
import com.db.desafio.votacao.v1.modules.votacao.models.PautaStub;

@DisplayName("Pauta service")
public class PautaServiceTest 
{
    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    private Pauta mockPautaWithId = PautaStub.createPautaWithId();
    private Pauta mockPautaWithWrongDates = PautaStub.createPautaWithWrongDates();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( pautaRepository.save( any( Pauta.class ))).thenReturn( mockPautaWithId );
        when( pautaRepository.findById( 1L )).thenReturn( Optional.of( mockPautaWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao criar pauta")
    public void Should_ReturnOk_CreatePauta()
    {
        Pauta pauta = pautaService.addPauta( mockPautaWithId );
        
        assertEquals( mockPautaWithId, pauta );
        verify( pautaRepository, times( 1 )).save( any( Pauta.class ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar BadRequest ao criar pauta com datas inválidas")
    public void Should_ReturnBadRequest_CreatePauta()
    {
        BadRequestException exception = assertThrows( BadRequestException.class, 
                                                    () -> pautaService.addPauta( mockPautaWithWrongDates ));

        assertEquals( "A pauta '" + mockPautaWithWrongDates.getName() + "' não pode ter data inicial anterior a data atual.", exception.getMessage() );
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar pauta")
    public void Should_ReturnOk_GetPauta()
    {
        Pauta pauta = pautaService.getPautaById( 1L );

        assertEquals( mockPautaWithId, pauta );
        verify( pautaRepository, times( 1 )).findById( any( Long.class ));
    }
    
    @Test
    @DisplayName("[SERVICE] Deve retornar NotFound ao buscar resultado da pauta")
    public void Should_ReturnNotFound_GetPautaResult()
    {
        long pautaId = -1;

        NotFoundException exception = assertThrows( NotFoundException.class, 
                                                    () -> pautaService.getPautaResult( pautaId ));

        assertEquals( "Pauta não localizada para o id: #" + pautaId , exception.getMessage());
    }
}
