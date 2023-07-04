package br.com.microservices.clienteemprestimo.dto;

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

    @Data
    public static class Endereco {
        private String cep;
        private String logradouro;
        private String cidade;
        private String numero;
        private String complemento;

    }


}
