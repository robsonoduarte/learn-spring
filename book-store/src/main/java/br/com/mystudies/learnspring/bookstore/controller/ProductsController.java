package br.com.mystudies.learnspring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.mystudies.learnspring.bookstore.domain.Product;

@Controller
public class ProductsController {


	@GetMapping(value="/products/new")
	public String form() {
		return "/products/form";
	}


	public String save(Product product) {
		return "";
	}




}
