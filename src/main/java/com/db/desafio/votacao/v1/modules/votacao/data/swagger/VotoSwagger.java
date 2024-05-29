
package com.db.desafio.votacao.v1.modules.votacao.data.swagger;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.db.desafio.votacao.v1.config.SwaggerConfig;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterVotoDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Voto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface VotoSwagger 
{
    final String TAG_NAME = "Votos";

    @Operation(
        operationId = "buscar votos",
        summary = "Busca todos votos",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Voto.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<List<Voto>> getVotos();
    
    @Operation(
        operationId = "registra voto",
        summary = "Registra um voto em uma pauta",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Voto.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "400",
                description = SwaggerConfig.BAD_REQUEST_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Voto> createVoto( @RequestBody RegisterVotoDTO votoDTO );
}
