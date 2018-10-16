package br.com.mystudies.springboot.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.mystudies.springboot.domain.Car;

@Repository
public interface CarRepository extends ReactiveMongoRepository<Car, String> {}
