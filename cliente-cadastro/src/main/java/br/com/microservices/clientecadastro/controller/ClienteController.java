package br.com.microservices.clientecadastro.controller;

import br.com.microservices.clientecadastro.dto.ClienteDTO;
import br.com.microservices.clientecadastro.dto.ClienteInputDTO;
import br.com.microservices.clientecadastro.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable UUID id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody @Valid ClienteInputDTO inputDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(inputDTO));
    }

}
