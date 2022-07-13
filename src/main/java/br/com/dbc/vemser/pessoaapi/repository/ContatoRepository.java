package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.enums.TipoContato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*1*/, 1, TipoContato.COMERCIAL, "12345678910", "whatsapp"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*2*/, 2, TipoContato.RESIDENCIAL, "6756638848", "telefone"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*3*/, 3, TipoContato.COMERCIAL, "8857636749", "telefone"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*4*/, 4, TipoContato.RESIDENCIAL, "788476366443", "whatsapp"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*5*/, 5, TipoContato.COMERCIAL, "775884773994", "whatsapp"));
    }

    public Contato create(Contato contato){
        contato.setIdContato(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list() {
        return listaContatos;
    }
}
