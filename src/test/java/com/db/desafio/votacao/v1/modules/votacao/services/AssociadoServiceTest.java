
package com.db.desafio.votacao.v1.modules.votacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;
import com.db.desafio.votacao.v1.modules.votacao.data.repositories.AssociadoRepository;
import com.db.desafio.votacao.v1.modules.votacao.models.AssociadoStub;

@DisplayName("Associado service")
public class AssociadoServiceTest 
{
    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    private Associado mockAssociadoWithId = AssociadoStub.createAssociadoWithId();

    @BeforeEach
    public void initializer()
    {
        openMocks(this);

        when( associadoRepository.save( any( Associado.class ))).thenReturn( mockAssociadoWithId );
        when( associadoRepository.findByDocument( "65977371055" )).thenReturn( Optional.of( mockAssociadoWithId ));
    }

    @Test
    @DisplayName("[SERVICE] Deve retornar Ok ao buscar Associado por documento")
    public void Should_ReturnOk_GetAssociado()
    {
        Associado associado = associadoService.getAssociadoByDocument( "65977371055" );

        assertEquals( mockAssociadoWithId, associado );
        verify( associadoRepository, times( 1 )).findByDocument( any( String.class ));
    }
}
