package br.com.mystudies.springboot.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class Book {


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=IDENTITY)
	private Long id;

	@Column(name="TITLE")
	public String title;

	@Column(name="DESCRIPTION")
	public String description;

	@Column(name="PAGES")
	public int pages;

}
