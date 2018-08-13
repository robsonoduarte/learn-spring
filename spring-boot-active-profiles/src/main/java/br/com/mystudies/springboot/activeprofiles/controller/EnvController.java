package br.com.mystudies.springboot.activeprofiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

	@Autowired
	private Environment env;

	@GetMapping(value="/env/properties")
	public String getProperties() {
		return "Common property to application => " + env.getProperty("common.property")
				+ " | Property by environment => " + env.getProperty("property.by.environment");
	}


}
