package br.com.mystudies.springboot.domain;

import java.util.Date;

import lombok.Value;

@Value
public class CarEvents {

	private Car model;
	private Date when;
}
