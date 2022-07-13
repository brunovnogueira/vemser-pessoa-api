package br.com.dbc.vemser.pessoaapi.enums;

import java.util.Arrays;

public enum TipoContato {
    RESIDENCIAL(1),
    COMERCIAL(2);

    private Integer tipoContato;

    TipoContato(Integer tipoContato){
        this.tipoContato = tipoContato;
    }

    public Integer getTipoContato() {
        return tipoContato;
    }
    public static TipoContato ofTipo(Integer tipo) {
        return Arrays.stream(TipoContato.values())
                .filter(tp -> tp.getTipoContato().equals(tipo))
                .findFirst()
                .get();
    }
}
