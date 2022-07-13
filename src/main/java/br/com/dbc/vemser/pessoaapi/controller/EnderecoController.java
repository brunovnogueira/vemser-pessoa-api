package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTOCreate;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Listar endereços", description = "Lista todos os endereços do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de endereços"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<EnderecoDTO> list(){
        return enderecoService.list();
    }

    @Operation(summary = "Listar endereço pelo id", description = "Lista o endereço do id correspondente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço do id correspondente"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idEndereco}")
    public List<EnderecoDTO> listPorId(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException {
        return enderecoService.listPorId(id);
    }

    @Operation(summary = "Listar endereços pelo id da pessoa", description = "Lista todos os endereços da pessoa com id correspondente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de enderescos da pessoa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listPorIdPessoa(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        return enderecoService.listPorIdPessoa(id);
    }

    @Operation(summary = "Criar um endereço", description = "Cria um novo endereço no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@PathVariable("idPessoa") Integer idPessoa, @RequestBody @Valid EnderecoDTOCreate endereco) throws RegraDeNegocioException {
        return ResponseEntity.ok(enderecoService.create(idPessoa, endereco));
    }

    @Operation(summary = "Atualizar endereço", description = "Atualiza o endereço no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") Integer idEndereco, @RequestBody @Valid EnderecoDTOCreate endereco) throws RegraDeNegocioException {
        return ResponseEntity.ok(enderecoService.update(idEndereco, endereco));
    }

    @Operation(summary = "Deletar endereço", description = "Deleta o endereço do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idEndereco}")
    public void delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException {
        enderecoService.delete(id);
    }
}
