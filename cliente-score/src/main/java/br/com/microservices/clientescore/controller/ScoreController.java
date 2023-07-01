package br.com.microservices.clientescore.controller;

import br.com.microservices.clientescore.client.ClienteCadastroProxy;
import br.com.microservices.clientescore.config.IdadePercentualConfigProperties;
import br.com.microservices.clientescore.dto.ClienteDTO;
import br.com.microservices.clientescore.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(value = "/score", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping("/{id}")
    public String valorComPercentualAcrescido(@PathVariable UUID id, @RequestParam BigDecimal valor) {
       return scoreService.obterValorAcrescido(id, valor);
    }

}
