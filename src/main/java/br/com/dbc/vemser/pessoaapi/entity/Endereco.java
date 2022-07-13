package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.enums.TipoEndereco;
import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private Integer idPessoa;
    private Integer idEndereco;
    private TipoEndereco tipo;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}
