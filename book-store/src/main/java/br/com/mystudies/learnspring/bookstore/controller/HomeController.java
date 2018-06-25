package br.com.mystudies.learnspring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping
	public void index() {
		System.out.println("Carregando produtos...");
	}
}
