package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PessoaDTOCreate {
    @Schema(description = "Nome da Pessoa", example = "Bruno")
    @NotBlank
    private String nome;
    @Schema(description = "Data de nascimento", example = "1993-09-28")
    @NotNull
    private LocalDate dataNascimento;
    @Schema(description = "CPF da pessoa", example = "314.725.158-62")
    @CPF
    @NotBlank
    private String cpf;
    @Schema(description = "Email da pessoa", example = "user@gmail.com")
    @Email
    @NotBlank
    private String email;
}
