
package com.db.desafio.votacao.v1.modules.votacao.data.swagger;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.db.desafio.votacao.v1.config.SwaggerConfig;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.PautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.dtos.RegisterPautaDTO;
import com.db.desafio.votacao.v1.modules.votacao.data.models.Pauta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PautaSwagger 
{
    final String TAG_NAME = "Pauta";

    @Operation(
        operationId = "buscar pautas",
        summary = "Busca todas pautas",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Pauta.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<List<Pauta>> getPautas();

    @Operation(
        operationId = "cadastra pauta",
        summary = "Cadastra uma nova pauta",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Pauta.class ),
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
            ),
            @ApiResponse(
                responseCode = "404",
                description = SwaggerConfig.NOT_FOUND_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<Pauta> createPauta( @RequestBody RegisterPautaDTO pautaDTO );

    @Operation(
        operationId = "busca resultado pauta",
        summary = "Busca os resultados de uma pauta",
        tags = { TAG_NAME },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = SwaggerConfig.SUCCESS_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = PautaDTO.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            ),
            @ApiResponse(
                responseCode = "404",
                description = SwaggerConfig.NOT_FOUND_MESSAGE,
                content = @Content( 
                            schema = @Schema( implementation = Error.class ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
            )
        }
    )
    public ResponseEntity<PautaDTO> getPautaResult( @PathVariable("pautaId") long pautaId );
}
