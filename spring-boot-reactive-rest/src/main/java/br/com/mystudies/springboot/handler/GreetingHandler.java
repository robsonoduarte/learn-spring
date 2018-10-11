package br.com.mystudies.springboot.handler;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

	public Mono<ServerResponse> hello(ServerRequest request){
		return ok().contentType(TEXT_PLAIN).body(fromObject("Hello, Spring"));
	}
}
