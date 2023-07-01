package br.com.microservices.clienteemprestimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClienteEmprestimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteEmprestimoApplication.class, args);
	}

}
