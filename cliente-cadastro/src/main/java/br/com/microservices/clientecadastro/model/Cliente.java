package br.com.microservices.clientecadastro.model;

import br.com.microservices.clientecadastro.model.embeddable.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    @Embedded
    private Endereco endereco;
    private LocalDate dataNascimento;
    @CreationTimestamp
    private LocalDate dataCriacao;

}
