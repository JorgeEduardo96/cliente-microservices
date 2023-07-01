package br.com.microservices.clientescore.client;


import br.com.microservices.clientescore.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cliente-cadastro")
public interface ClienteCadastroProxy {

    @GetMapping("/cliente/{id}")
    ClienteDTO findClienteById(@PathVariable UUID id);

}
