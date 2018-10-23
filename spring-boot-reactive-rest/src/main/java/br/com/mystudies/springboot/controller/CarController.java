package br.com.mystudies.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystudies.springboot.domain.Car;
import br.com.mystudies.springboot.service.FluxCarService;
import reactor.core.publisher.Flux;

@RestController
public class CarController {

	@Autowired
	private FluxCarService service;


	@GetMapping("/cars")
	public Flux<Car> all(){
		return service.all();
	}


}
