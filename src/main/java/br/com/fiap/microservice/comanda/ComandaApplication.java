package br.com.fiap.microservice.comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ComandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComandaApplication.class, args);
	}

}