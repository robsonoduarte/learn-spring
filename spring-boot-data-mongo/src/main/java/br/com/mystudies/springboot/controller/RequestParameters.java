package br.com.mystudies.springboot.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RequestParameters {
	private String portal;
	private int page;
}
