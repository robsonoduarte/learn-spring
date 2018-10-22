package br.com.mystudies.springboot.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

/*		Files.lines(Paths.get(Temp.class.getResource("/source/cars").toURI()))
			.map(line -> asList(line.split(",")).stream())
			.flatMap(car -> new Car("aaa", car));*/

		// TODO Auto-generated method stub
		System.out.println(" 1 2 3 ....");
	}

}
