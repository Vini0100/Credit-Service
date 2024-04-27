package io.github.vini0100.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient // Para poder se registrar no Eureka
@EnableDiscoveryClient // Estará habilitada para se conectar a qualquer serviço de registro
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	@Bean // Quando o Spring inicializa, executa esse método
	public RouteLocator routes(RouteLocatorBuilder builder) { // Um RouteLocator é usado pelo Spring Cloud Gateway
																// para definir as rotas que o gateway irá manipular.
		return builder
				.routes()
				.route(r -> r.path("/clientes/**").uri("lb://msclientes")) // Encaminhada para o serviço msclientes
																			// através do LoadBalancer.
				.route(r -> r.path("/cartoes/**").uri("lb://mscartoes")) // Encaminhada para o serviço mscartoes
																			// através do LoadBalancer.
				.route(r -> r.path("/avaliacoes-credito/**").uri("lb://msavaliadorcredito"))
				// Encaminhada para o serviço msavaliadorcredito através do LoadBalancer.
				.build();
	}

}
