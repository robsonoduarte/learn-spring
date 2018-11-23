package br.com.mystudies.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystudies.springboot.domain.Car;
import br.com.mystudies.springboot.repo.CarRepository;

@RestController
public class CarController {

	@Autowired
	private CarRepository repository;


	@GetMapping("/noreactive/cars")
	public List<Car> all(){
		return repository.findAll();
	}


}
