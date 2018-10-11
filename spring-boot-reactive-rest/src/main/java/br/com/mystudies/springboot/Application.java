package br.com.mystudies.springboot;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mystudies.springboot.client.GreetingWebClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		run(Application.class, args);
		System.out.println(new GreetingWebClient().getResult());
	}
}
