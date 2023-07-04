package br.com.microservices.clienteemprestimo.client;


import br.com.microservices.clienteemprestimo.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cliente-cadastro")
public interface ClienteCadastroProxy {

    @GetMapping("/clientes/{id}")
    ClienteDTO findClienteById(@PathVariable UUID id);

}
