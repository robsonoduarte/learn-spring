package br.com.mystudies.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mystudies.springboot.domain.Car;
import br.com.mystudies.springboot.repo.CarReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxCarService {


	@Autowired
	private CarReactiveRepository repository;


	public Flux<Car> all(){
		return repository.findAll();
	}



	public Mono<Car> byId(String id){
		return repository.findById(id);
	}





/*	public Flux<Car> streams(String id){

	}*/






}
