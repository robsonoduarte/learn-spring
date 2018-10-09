package br.com.mystudies.springboot.dataloader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.repository.ImmobileRepository;

@Component
public class MongoSourceLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoSourceLoader.class);

	@Autowired
	private ImmobileRepository immobileRepository;


	@Autowired
	private SourceLoader grupoZapSourceLoader;



	public void loadSourceInMongo() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<String> lines = grupoZapSourceLoader.loadLines();

		Immobile immobile = null;

		for (String json : lines) {
			LOGGER.info("Save the following source -> " + json);
			immobile = mapper.readValue(json, Immobile.class);
			immobileRepository.save(immobile);
		}
	}

}
