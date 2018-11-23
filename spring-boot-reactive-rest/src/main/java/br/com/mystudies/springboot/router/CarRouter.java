package br.com.mystudies.springboot.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.mystudies.springboot.handler.CarHandler;

@Configuration
public class CarRouter {
	@Bean
	public RouterFunction<ServerResponse> route2(CarHandler carHandler){
		return RouterFunctions.route(GET("/reactive/cars"),carHandler::allCars);
	}
}
