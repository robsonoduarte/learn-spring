package br.com.mystudies.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.service.ImmobileParameters;
import br.com.mystudies.springboot.service.ImmobileService;

@RestController
public class ImmobileController {

	@Autowired
	private ImmobileService immobileService;


	@GetMapping("/properties/sale")
	public Page<Immobile> getPropertiesToSale(RequestParameters parameters){
		return immobileService.getPropertiesToSale(immobileParameters(parameters));
	}


	@GetMapping("/properties/rental")
	public Page<Immobile> getPropertiesToRental(RequestParameters parameters){
		return immobileService.getPropertiesToRental(immobileParameters(parameters));
	}






	private ImmobileParameters immobileParameters(RequestParameters parameters) {
		return ImmobileParameters.builder()
				.page(parameters.getPage())
				.portal(parameters.getPortal())
				.build();
	}




}
