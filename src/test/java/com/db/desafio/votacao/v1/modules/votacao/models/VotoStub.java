
package com.db.desafio.votacao.v1.modules.votacao.models;

import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterVotoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.enums.VotoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;

public class VotoStub 
{
    /**
     * createRegisterVoto
     * 
     * @return RegisterVotoDTO
     */
    public static RegisterVotoDTO createRegisterVoto()
    {
        RegisterVotoDTO votoDTO = RegisterVotoDTO.builder()
                                                .document("65977371055")
                                                .pautaId( 1L )
                                                .voto( VotoEnum.SIM )
                                                .build();
        return votoDTO;
    }

    /**
     * createVotoWithId
     * 
     * @return
     */
    public static Voto createVotoWithId()
    {
        Voto voto = Voto.builder()
                        .id( 1 )
                        .associado( AssociadoStub.createAssociadoWithId() )
                        .pauta( PautaStub.createPautaWithId() )
                        .voto( VotoEnum.SIM ).build();

        return voto;
    }
}
