package br.com.microservices.clienteemprestimo.controller;

import br.com.microservices.clienteemprestimo.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(value = "/emprestimo", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping("/{id}")
    public String valorComPercentualAcrescido(@PathVariable UUID id, @RequestParam BigDecimal valor, @RequestParam int meses) {
        log.info("EmprestimoController - valorComPercentualAcrescido -> id: {}, valor: {}, meses: {}", id, valor, meses);
       return emprestimoService.obterValorEmprestimo(id, valor, meses);
    }

}
