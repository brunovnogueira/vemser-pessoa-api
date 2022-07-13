package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTOCreate;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ObjectMapper objectMapper;
    public ContatoService(){

    }

    public ContatoDTO create(ContatoDTOCreate contato, Integer idPessoa) throws RegraDeNegocioException {
        Pessoa pessoaValida = pessoaService.findById(idPessoa);
        Contato contatoEntity = objectMapper.convertValue(contato,Contato.class);
        log.info("Criando contato...");
        Contato contatoCriado = contatoRepository.create(contatoEntity);
        contatoEntity.setIdPessoa(idPessoa);
        log.info("Contato criado!");
        return objectMapper.convertValue(contatoEntity,ContatoDTO.class);
    }
    public List<ContatoDTO> list(){
        return contatoRepository.list().stream().map(contato -> objectMapper.convertValue(contato,ContatoDTO.class)).collect(Collectors.toList());
    }
    public ContatoDTO update(Integer id,
                         ContatoDTOCreate contatoAtualizar) throws RegraDeNegocioException {
        pessoaService.findById(contatoAtualizar.getIdPessoa());
        Contato contatoRecuperado = findById(id);
        objectMapper.convertValue(contatoAtualizar,Contato.class);
        log.info("Atualizando contato...");
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        log.info("Contato atualizado!");
        return objectMapper.convertValue(contatoRecuperado,ContatoDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Contato contato = findById(id);
        log.info("Deletando contato....");
        contatoRepository.list().remove(contato);
        log.info("Contato deletado");
    }

    public List<ContatoDTO> listByIdPessoa(Integer id) throws RegraDeNegocioException {
        pessoaService.findById(id);
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .map(contato -> objectMapper.convertValue(contato,ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public Contato findById(Integer idContato) throws RegraDeNegocioException {
        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(idContato))
                .findFirst()
                .orElseThrow(()->new RegraDeNegocioException("Contato não cadastrado"));
        return contatoRecuperado;
    }
}
