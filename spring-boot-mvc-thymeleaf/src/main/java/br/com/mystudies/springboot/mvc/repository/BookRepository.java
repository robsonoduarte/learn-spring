package br.com.mystudies.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mystudies.springboot.mvc.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
