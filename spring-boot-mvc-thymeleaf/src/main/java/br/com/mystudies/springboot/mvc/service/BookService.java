package br.com.mystudies.springboot.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mystudies.springboot.mvc.domain.Book;
import br.com.mystudies.springboot.mvc.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public Book save(Book book) {
		return repository.save(book);
	}

}
