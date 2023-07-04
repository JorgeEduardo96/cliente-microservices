package br.com.microservices.clientecadastro.dto;

import br.com.microservices.clientecadastro.model.embeddable.Endereco;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ClienteDTO {

    private UUID id;
    private String nome;
    private String email;
    private Endereco endereco;
    private LocalDate dataNascimento;
}
