
package com.db.desafio.votacao.v1.modules.votacao.models;

import com.db.desafio.votacao.v1.modules.votacao.data.enums.AssociadoEnum;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Associado;

public class AssociadoStub 
{
    /**
     * createAssociadoWithoutId
     * 
     * @return Associado
     */
    public static Associado createAssociadoWithId()
    {
        Associado associado = Associado.builder()
                                        .id( 1 )
                                        .name("Lucas Kissmann")
                                        .document("65977371055")
                                        .status( AssociadoEnum.ABLE_TO_VOTE )
                                        .build();

        return associado;
    }

    /**
     * createAssociadoWithoutId
     * 
     * @return Associado
     */
    public static Associado createAssociadoWithoutId()
    {
        Associado associado = Associado.builder()
                                        .name("Lucas Kissmann")
                                        .document("65977371055")
                                        .build();
        return associado;
    }
}
