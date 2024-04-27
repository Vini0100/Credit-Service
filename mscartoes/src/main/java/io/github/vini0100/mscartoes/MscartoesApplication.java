package io.github.vini0100.mscartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // Os microserviços também devem ter este anotação para poderem ser encontrados
					// pelo Eureka
@EnableRabbit // Habilita o uso de RabbitMQ
public class MscartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
