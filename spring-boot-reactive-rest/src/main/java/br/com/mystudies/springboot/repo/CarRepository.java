package br.com.mystudies.springboot.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mystudies.springboot.domain.Car;

public interface CarRepository extends MongoRepository<Car, String>{

}
