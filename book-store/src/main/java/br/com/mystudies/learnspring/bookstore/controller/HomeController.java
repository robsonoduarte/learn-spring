package br.com.mystudies.learnspring.bookstore.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

	@GetMapping()
	@ResponseStatus(OK)
	public void index() {
		System.out.println("Carregando produtos...");
	}
	
}
