package br.com.microservices.clientecadastro.service;

import br.com.microservices.clientecadastro.dto.ClienteDTO;
import br.com.microservices.clientecadastro.dto.ClienteInputDTO;
import br.com.microservices.clientecadastro.exception.NotFoundException;
import br.com.microservices.clientecadastro.model.Cliente;
import br.com.microservices.clientecadastro.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteDTO findById(UUID id) {
        return fromModelToDto(repository.findById(id).orElseThrow(()
                -> new NotFoundException("Cliente " + id + " não existe na base de dados.")));
    }

    public ClienteDTO save(ClienteInputDTO dto) {
        Cliente model = repository.save(fromInputDtoToModel(dto));
        log.info("Cliente (nome: {}, id: {}) salvo com sucesso!", model.getNome(), model.getId());
        return fromModelToDto(model);
    }

    private Cliente fromInputDtoToModel(ClienteInputDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .dataNascimento(dto.getDataNascimento())
                .endereco(dto.getEndereco())
                .build();
    }

    private ClienteDTO fromModelToDto(Cliente model) {
        return ClienteDTO.builder()
                .id(model.getId())
                .nome(model.getNome())
                .email(model.getEmail())
                .dataNascimento(model.getDataNascimento())
                .endereco(model.getEndereco())
                .build();
    }

}
