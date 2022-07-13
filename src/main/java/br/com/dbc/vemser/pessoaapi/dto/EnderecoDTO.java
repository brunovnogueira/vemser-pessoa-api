package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EnderecoDTO extends EnderecoDTOCreate {
    @Schema(description = "Id do endereço")
    private Integer idEndereco;
}
