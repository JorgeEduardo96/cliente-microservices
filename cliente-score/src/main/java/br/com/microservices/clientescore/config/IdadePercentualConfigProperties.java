package br.com.microservices.clientescore.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConfigurationProperties("cliente-score")
@Getter
@Setter
public class IdadePercentualConfigProperties {

    private BigDecimal jovem;
    private BigDecimal adulto;
    private BigDecimal senior;

}
