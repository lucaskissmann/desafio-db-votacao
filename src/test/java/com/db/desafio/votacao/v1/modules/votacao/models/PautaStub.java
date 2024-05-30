
package com.db.desafio.votacao.v1.modules.votacao.models;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;

public class PautaStub 
{
    /**
     * createPautaWithId
     * 
     * @return RegisterPautaDTO
     */
    public static Pauta createPautaWithId()
    {
        Pauta pauta = Pauta.builder()
                            .id( 1L )
                            .name( "Pauta teste" )
                            .startDate( ApplicationContext.now() )
                            .endDate( ApplicationContext.now().plusMinutes( 1 ))
                            .build();
        return pauta;
    }
    
    /**
     * createPautaWithWrongDates
     * 
     * @return Pauta
     */
    public static Pauta createPautaWithWrongDates()
    {
        Pauta pauta = Pauta.builder()
                            .name("Pauta teste")
                            .startDate( ApplicationContext.now().minusDays( 1 ))
                            .endDate( ApplicationContext.now().plusMinutes( 1 ))
                            .build();
        return pauta;
    }

    /**
     * createPautaWithoutId
     * 
     * @return RegisterPautaDTO
     */
    public static RegisterPautaDTO createPautaDTOWithoutId()
    {
        RegisterPautaDTO pauta = RegisterPautaDTO.builder()
                            .name("Pauta teste")
                            .assembleiaId( 1L )
                            .startDate( ApplicationContext.now() )
                            .endDate( ApplicationContext.now().plusMinutes( 1 ))
                            .build();

        return pauta;
    }

    /**
     * createPautaWithWrongDates
     * 
     * @return RegisterPautaDTO
     */
    public static RegisterPautaDTO createPautaDTOWithWrongDates()
    {
        RegisterPautaDTO pauta = RegisterPautaDTO.builder()
                            .name("Pauta teste")
                            .assembleiaId( 1L )
                            .startDate( ApplicationContext.now().minusDays( 1 ))
                            .endDate( ApplicationContext.now().plusMinutes( 1 ))
                            .build();

        return pauta;
    }
}
