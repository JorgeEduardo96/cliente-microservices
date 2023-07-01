package br.com.microservices.clienteemprestimo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConfigurationProperties("percentuais-emprestimo")
@Getter
@Setter
public class PercentuaisConfigurationProperties {

    private BigDecimal jovem;
    private BigDecimal adulto;
    private BigDecimal senior;
    private BigDecimal taxaJuros;

}
