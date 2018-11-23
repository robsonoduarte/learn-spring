package br.com.mystudies.springboot.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.mystudies.springboot.domain.Car;
import br.com.mystudies.springboot.repo.CarReactiveRepository;
import reactor.core.publisher.Mono;

@Component
public class CarHandler {

	@Autowired
	private CarReactiveRepository repository;

	 public Mono<ServerResponse> allCars(ServerRequest serverRequest) {
	       return ServerResponse.ok()
	               .body(repository.findAll(), Car.class)
	               .doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
	   }

}
