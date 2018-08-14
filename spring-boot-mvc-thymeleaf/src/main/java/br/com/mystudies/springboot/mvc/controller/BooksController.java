package br.com.mystudies.springboot.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mystudies.springboot.mvc.domain.Book;
import br.com.mystudies.springboot.mvc.service.BookService;

@Controller
@RequestMapping("/books")
public class BooksController {


	@Autowired
	private BookService service;



	@GetMapping(value="/form")
	public String form() {
		return "/books/form";
	}

	/*@PostMapping(value="/save")*/
	@GetMapping(value="/save")
	public String save() {
		Book book = new Book();
		book.title = "test";
		book.description = "test";
		book.pages = 1 ;
		service.save(book);
		return "/books/list";
	}

	@GetMapping(value="/list")
	public String list() {
		return "/books/list";
	}

}
