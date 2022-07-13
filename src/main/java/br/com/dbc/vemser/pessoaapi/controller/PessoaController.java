package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTOCreate;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.EmailService;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/pessoa") // localhost:8080/pessoa
public class PessoaController {
    @Autowired //não preciso mais instanciar um new PessoaService()
    private PessoaService pessoaService;
    @Autowired
    private EmailService emailService;
//    @Autowired
//    private PropertyReader propertyReader;

//    //Modelo novo (depreciamento do AutoWired)
//    private final PessoaService pessoaService;
//    public PessoaController(PessoaService pessoaService) {
//        this.pessoaService = pessoaService;
//    }


//    @GetMapping("/hello") // localhost:8080/pessoa/hello
//    public String hello() {
//        return "Hello world!";
//    }

//    @GetMapping("/ambiente")
//    public String propertyReader(){
//        return propertyReader.getAmbiente();
//    }

//    @GetMapping("/email")
//    public String email() throws MessagingException {
//        //emailService.sendSimpleMessage();
//        //emailService.sendWithAttachment();
//        //emailService.sendEmail();
//        return "Enviando email!";
//    }
    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // localhost:8080/pessoa
    public List<PessoaDTO> list() {
        return pessoaService.list();
    }

    @Operation(summary = "Listar pessoas por nome", description = "Lista as pessoas com o nome buscado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas com o nome buscado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{nome}") // localhost:8080/pessoa/byname?nome=Rafa
    public List<PessoaDTO> listByName(@PathVariable("nome") String nome) {
        return pessoaService.listByName(nome);
    }

    @Operation(summary = "Criar uma nova pessoa", description = "Cria uma pessoa com os dados fornecidos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa criada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // localhost:8080/pessoa
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTOCreate pessoa){
        return ResponseEntity.ok(pessoaService.create(pessoa));
        //return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.OK)
    }

    @Operation(summary = "Atualizar os dados de uma pessoa", description = "Faz a atualização dos dados de uma pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados atualizados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @RequestBody @Valid PessoaDTOCreate pessoaAtualizar) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.update(id, pessoaAtualizar));
    }

    @Operation(summary = "Deletar os dados da pessoa", description = "Deleta os dados da pessoa com o id fornecido")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados deletados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        pessoaService.delete(id);
    }
}
