package br.com.mystudies.learnspring.bookstore.main;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="br.com.mystudies")
public class Application{
	public static void main(String[] args){
		run(Application.class, args);
	}
}