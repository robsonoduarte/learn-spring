package br.com.mystudies.learnspring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductsController {


	@GetMapping(value="/products/new")
	public String form() {
		return "/products/new";
	}


	@PostMapping(value="/products/save")
	public String save() {
		return "";
	}

	@GetMapping(value="/products/list")
	public String list() {
		return "/products/products-form";
	}


}
