package io.github.vini0100.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // Os microserviços também devem ter este anotação para poderem ser encontrados pelo Eureka
public class MsclientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsclientesApplication.class, args);
	}

}
