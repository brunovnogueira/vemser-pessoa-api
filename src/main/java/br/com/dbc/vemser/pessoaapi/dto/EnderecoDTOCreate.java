package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EnderecoDTOCreate {
    @Schema(description = "Id da pessoa")
    private Integer idPessoa;
    @Schema(description = "Tipo do endereço", example = "1 para COMERCIAL ou 2 para RESIDENCIAL")
    @NotNull
    private TipoEndereco tipo;
    @Schema(description = "Logradouro do endereço", example = "Rua Dois")
    @NotBlank
    @Size(max = 250)
    private String logradouro;
    @Schema(description = "Número do endereço", example = "1000")
    @NotNull
    private Integer numero;
    @Schema(description = "Complemento do endereço")
    private String complemento;
    @Schema(description = "CEP do endereço", example = "02412005")
    @NotBlank
    @Size(max = 8)
    private String cep;
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    @NotEmpty
    @Size(max = 250)
    private String cidade;
    @Schema(description = "Estado do endereço", example = "SP")
    @NotNull
    private String estado;
    @Schema(description = "País do endereço", example = "Brasil")
    @NotNull
    private String pais;
}
