package com.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Permitir el acceso a la ruta de la api usuarios
				.route("usuarios", r -> r.path("/usuarios/**")
						.uri("lb://usuarios:8702"))
				// Permitir el acceso a la ruta de la api reservas
				.route("reservas", r -> r.path("/reservas/**")
						.uri("lb://reservas:8701"))
				// Permitir el acceso al uso de la API comentarios
				.route("empleadosQLRoute", r -> r.path("/empleadosQL/**")
						.filters(f -> f.rewritePath("/empleadosQL/(?<segment>.*)", "/${segment}"))
						.uri("lb://empleadosQL:8706"))
				.route("empleadosQLGraph", r -> r.path("/empleadosQL706")
						.uri("lb://empleadosQL:8706/empleadosQL706"))
				.build();
	}

}
