package br.com.microservices.clientecadastro.model.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;

}
