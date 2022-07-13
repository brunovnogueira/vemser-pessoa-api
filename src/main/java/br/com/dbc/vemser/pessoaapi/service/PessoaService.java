package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTOCreate;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmailService emailService;
    public PessoaService(){

    }

    public PessoaDTO create(PessoaDTOCreate pessoa){
        log.info("Criando pessoa...");
        Pessoa pessoaEntity = objectMapper.convertValue(pessoa,Pessoa.class);
        pessoaRepository.create(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
        log.info("Pessoa criada!");
        emailService.sendEmailPessoaCadastrada(pessoaDTO);
        return pessoaDTO;
    }

    public List<PessoaDTO> list(){

        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa,PessoaDTO.class)).collect(Collectors.toList());

    }

    public PessoaDTO update(Integer id,
                         PessoaDTOCreate pessoaAtualizar) throws RegraDeNegocioException {
        Pessoa pessoaEntity = objectMapper.convertValue(pessoaAtualizar,Pessoa.class);
        Pessoa pessoaRecuperada = findById(id);
        log.info("Atualizando pessoa...");
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        log.info("Pessoa atualizada!");
        return objectMapper.convertValue(pessoaRecuperada,PessoaDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Pessoa pessoa = findById(id);
        log.info("Deletando pessoa....");
        pessoaRepository.list().remove(pessoa);
        log.info("Pessoa deletada!");
    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class)).collect(Collectors.toList());
    }

    public Pessoa findById(Integer id) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
        return pessoaRecuperada;
    }
}
