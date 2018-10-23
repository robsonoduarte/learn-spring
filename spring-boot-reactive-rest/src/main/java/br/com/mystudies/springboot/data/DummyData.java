package br.com.mystudies.springboot.data;

import static java.util.stream.Collectors.toList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.domain.Car;
import br.com.mystudies.springboot.repo.CarRepository;

@Component
public class DummyData implements CommandLineRunner {


	private static final Logger LOGGER = LoggerFactory.getLogger(DummyData.class);


	@Autowired
	private CarRepository repository;



	@Override
	public void run(String... args) throws Exception {
		List<Car> cars =
				Files.lines(Paths.get(DummyData.class.getResource("/source/cars").toURI()))
					.flatMap(Pattern.compile(",")::splitAsStream)
					.map(model -> new Car(null, model))
					.collect(toList());


		repository.saveAll(cars).subscribe(car -> {
			LOGGER.info("Save the car --> " + car);
		});

	}

}
