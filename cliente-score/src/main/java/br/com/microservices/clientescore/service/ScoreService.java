package br.com.microservices.clientescore.service;

import br.com.microservices.clientescore.client.ClienteCadastroProxy;
import br.com.microservices.clientescore.config.IdadePercentualConfigProperties;
import br.com.microservices.clientescore.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private ClienteCadastroProxy clienteCadastroProxy;

    private IdadePercentualConfigProperties idadePercentualConfigProperties;

    public String obterValorAcrescido(UUID id, BigDecimal valor) {
        ClienteDTO cliente = clienteCadastroProxy.findClienteById(id);
//        BigDecimal valorAcrescido = valor + (valor * cliente.getDataNascimento().get)
    }

    private BigDecimal obterValorPorFaixaEtaria()
}
