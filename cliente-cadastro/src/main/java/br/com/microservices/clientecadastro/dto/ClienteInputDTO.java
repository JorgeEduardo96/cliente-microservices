package br.com.microservices.clientecadastro.dto;

import br.com.microservices.clientecadastro.model.embeddable.Endereco;
import br.com.microservices.clientecadastro.validaton.LevelSenha;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClienteInputDTO {

    @NotBlank
    private String nome;
    @Email
    private String email;
    @LevelSenha
    private String senha;
    @NotNull
    private Endereco endereco;
    @Past
    private LocalDate dataNascimento;

}
