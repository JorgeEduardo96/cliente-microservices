package br.com.microservices.clienteemprestimo.service;

import br.com.microservices.clienteemprestimo.client.ClienteCadastroProxy;
import br.com.microservices.clienteemprestimo.config.PercentuaisConfigurationProperties;
import br.com.microservices.clienteemprestimo.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmprestimoService {

    private final ClienteCadastroProxy clienteCadastroProxy;

    private final PercentuaisConfigurationProperties percentuaisConfigurationProperties;

    public String obterValorEmprestimo(UUID id, BigDecimal valor, int meses) {
        ClienteDTO cliente = clienteCadastroProxy.findClienteById(id);

        log.info("EmprestimoService - obterValorEmprestimo -> cliente: {}", cliente.getNome());

        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        return String.format("Cliente %s, solicitou um valor de R$ %s no prazo de %d meses. Após calculos efetuados, " +
                "a proposta é parcelas de R$ %s", cliente.getNome(), numberFormat.format(valor), meses,
                numberFormat.format(obterValorPorFaixaEtaria(cliente.getDataNascimento(), valor, meses)));
    }

    private BigDecimal obterValorPorFaixaEtaria(LocalDate dataNascimento, BigDecimal valor, int meses) {
        BigDecimal um = BigDecimal.ONE;
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        BigDecimal percentualAcrescimoIdade = idade > 18 && idade <= 30 ? percentuaisConfigurationProperties.getJovem() :
                idade > 31 && idade <= 64 ? percentuaisConfigurationProperties.getAdulto() : percentuaisConfigurationProperties.getSenior();
        log.info("EmprestimoService - obterValorPorFaixaEtaria -> idade: {}, percentualAcrescimoIdade: {}", idade ,
                percentualAcrescimoIdade);
        percentualAcrescimoIdade = percentualAcrescimoIdade.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        BigDecimal taxaJuros = (percentuaisConfigurationProperties.getTaxaJuros().divide(BigDecimal.valueOf(100),
                10, RoundingMode.HALF_UP)).add(percentualAcrescimoIdade);
        BigDecimal taxaMensal = taxaJuros.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        log.info("EmprestimoService - obterValorPorFaixaEtaria -> taxaMensal: {}, taxaJuros: {}", taxaMensal ,
                taxaJuros);
        return valor.multiply(taxaMensal).divide(um.subtract(um.divide(um.add(taxaMensal).pow(meses),
                10, RoundingMode.HALF_UP)), 10, RoundingMode.HALF_UP);
    }
}
