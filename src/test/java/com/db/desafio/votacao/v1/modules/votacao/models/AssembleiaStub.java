
package com.db.desafio.votacao.v1.modules.votacao.models;

import com.db.desafio.votacao.context.ApplicationContext;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Assembleia;

public class AssembleiaStub
{
    /**
     * createAssembleiaWithoutId
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithoutId()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .name("Assembleia teste")
                                            .startDate( ApplicationContext.today() )
                                            .endDate( ApplicationContext.today().plusDays( 1 ))
                                            .build();

        return assembleia;
    }
    
    /**
     * createAssembleiaWithWrongDates
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithWrongDates()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .name("Assembleia teste")
                                            .startDate( ApplicationContext.today().minusDays( 1 ))
                                            .endDate( ApplicationContext.today() )
                                            .build();
    
        return assembleia;
    }

    /**
     * createAssembleiaWithId
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithId()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .id( 1 )
                                            .name("Assembleia teste")
                                            .startDate( ApplicationContext.today() )
                                            .endDate( ApplicationContext.today().plusDays( 1 ))
                                            .build();

        return assembleia;
    }
    
    /**
     * createAssembleiaWithEndDateBefore
     * 
     * @return Assembleia
     */
    public static Assembleia createAssembleiaWithEndDateBefore()
    {
        Assembleia assembleia = Assembleia.builder()
                                            .id( 1 )
                                            .name("Assembleia teste")
                                            .startDate( ApplicationContext.today() )
                                            .endDate( ApplicationContext.today().minusDays( 1 ))
                                            .build();

        return assembleia;
    }
}
