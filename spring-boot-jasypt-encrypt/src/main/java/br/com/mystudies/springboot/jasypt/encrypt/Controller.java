package br.com.mystudies.springboot.jasypt.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private Environment env;

	@GetMapping(value="/property")
	public String getPropertyEncrypted() {
		return env.getProperty("property.encrypted");
	}
}
