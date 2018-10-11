package br.com.mystudies.springboot.client;

import static org.springframework.http.MediaType.TEXT_PLAIN;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class GreetingWebClient {

	private WebClient client = WebClient.create("http://localhost:8080");

	private Mono<ClientResponse> result = client.get()
			.uri("/hello")
			.accept(TEXT_PLAIN)
			.exchange();



	public String getResult() {
		return ">> result = " + result.flatMap( res -> res.bodyToMono(String.class)).block();
	}


}
