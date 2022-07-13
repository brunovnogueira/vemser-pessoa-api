package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.enums.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();

    private AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository (){

        listaEnderecos.add(new Endereco(1, COUNTER.incrementAndGet(), TipoEndereco.COMERCIAL, "Heitor Penteado", 103, "Pinheiros", "05412000","São Paulo", "SP", "Brasil"));
        listaEnderecos.add(new Endereco(2,COUNTER.incrementAndGet() ,TipoEndereco.COMERCIAL, "Rua Lucas Evangelista", 586, "Centro", "14700425","Bebedouro", "SP", "Brasil"));
        listaEnderecos.add(new Endereco(3,COUNTER.incrementAndGet() ,TipoEndereco.RESIDENCIAL, "Rua Esperança", 342, "Eldorado", "34589023","Curitiba", "PR", "Brasil"));
        listaEnderecos.add(new Endereco(4,COUNTER.incrementAndGet() ,TipoEndereco.COMERCIAL, "Quadra QE 40 Conjunto D", 814, "Guará", "71070042","Brasília", "DF", "Brasil"));
        listaEnderecos.add(new Endereco(5,COUNTER.incrementAndGet() ,TipoEndereco.RESIDENCIAL, "Rua Arlinda Gomes de Medeiros", 830, "Mirante", "58407615","Campina Grande", "PB", "Brasil"));

    }

    public List<Endereco> list () {
        return listaEnderecos;
    }

    public Endereco create(Endereco endereco){
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }
}
