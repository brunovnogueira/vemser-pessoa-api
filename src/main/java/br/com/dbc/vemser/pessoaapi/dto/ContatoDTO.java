package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContatoDTO extends ContatoDTOCreate {
    @Schema(description = "Id do contato")
    private Integer idContato;
}
