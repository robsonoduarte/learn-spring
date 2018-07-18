package br.com.mystudies.learnspring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {


	@GetMapping(value="/form")
	public String form() {
		return "/books/form";
	}

	@PostMapping(value="/save")
	public String save() {
		return "";
	}

	@GetMapping(value="/list")
	public String list() {
		return "/books/list";
	}

}
