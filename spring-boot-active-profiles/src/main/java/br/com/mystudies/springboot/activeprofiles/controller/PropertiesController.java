package br.com.mystudies.springboot.activeprofiles.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {


	@Value("${common.property}")
	private String commonProperty;


	@Value("${property.by.environment}")
	private String propertyByEnvironment;


	@GetMapping(value="/properties")
	public String getProperties() {
		return "Common property to application => " + commonProperty
				+ " | Property by environment => " + propertyByEnvironment;
	}

}
