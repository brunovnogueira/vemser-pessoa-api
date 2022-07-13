package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTOCreate;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    public ContatoController() {

    }

    //Listar Geral
    @Operation(summary = "Listar contatos", description = "Lista todas os contatos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de contatos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<ContatoDTO> list(){
        return contatoService.list();
    }

    //Listar por id pessoa
    @Operation(summary = "Listar os contatos pelo id da pessoa", description = "Lista todos os contatos pelo id da pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de Contatos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}")
    public List<ContatoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        return contatoService.listByIdPessoa(id);
    }

    //Adicionar
    @Operation(summary = "Criar um novo contato", description = "Cria um novo contato no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer idPessoa, @RequestBody @Valid ContatoDTOCreate contato) throws RegraDeNegocioException {
        return ResponseEntity.ok(contatoService.create(contato,idPessoa));
    }

    //Atualizar
    @Operation(summary = "Atualizar contato", description = "Atualiza um contato do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id, @RequestBody @Valid ContatoDTOCreate contatoAtualizar) throws RegraDeNegocioException{
        return ResponseEntity.ok(contatoService.update(id,contatoAtualizar));
    }

    //Deletar
    @Operation(summary = "Deletar contato", description = "Deleta um contato do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        contatoService.delete(id);
    }
}
