package br.com.mystudies.springboot;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Temp {

	public static void main(String[] args) throws Exception{

		Files.lines(Paths.get(Temp.class.getResource("/source/cars").toURI())).forEach( l ->{
						System.out.println(l);
					}
				);

	}
}
