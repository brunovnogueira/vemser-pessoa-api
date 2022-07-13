package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PessoaDTO extends PessoaDTOCreate {
    @Schema(description = "Id da pessoa")
    private Integer idPessoa;

}
