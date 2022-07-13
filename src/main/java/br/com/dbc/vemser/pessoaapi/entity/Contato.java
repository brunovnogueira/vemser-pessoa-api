package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.enums.TipoContato;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contato {
    private Integer idContato;
    private Integer idPessoa;
    private TipoContato tipoContato;
    private String numero;
    private String descricao;
}
