
package com.db.desafio.votacao.v1.helpers;

import com.db.desafio.votacao.v1.helpers.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Serializer 
{
    /**
     * json
     * 
     * @param objectToConvert T
     * @return String <T>
     */
    public static <T> String json( final T objectToConvert )
    {
        try 
        {
            ObjectMapper objectMapper = new ObjectMapper();
            
            objectMapper.registerModule( new JavaTimeModule() );

            return objectMapper.writeValueAsString( objectToConvert );
        } 
        catch( JsonProcessingException e )
        {
            throw new BadRequestException( e.getMessage() );
        }
    }
}
