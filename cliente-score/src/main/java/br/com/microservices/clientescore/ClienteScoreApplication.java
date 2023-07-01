package br.com.microservices.clientescore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClienteScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteScoreApplication.class, args);
	}

}
