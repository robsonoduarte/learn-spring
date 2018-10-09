package br.com.mystudies.springboot.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupSourceLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupSourceLoader.class);

	@Autowired
	private MongoSourceLoader mongoSourceLoader;


	@EventListener
	public void load(ContextRefreshedEvent event) throws Exception {
		LOGGER.info("Starting process to load source");
		mongoSourceLoader.loadSourceInMongo();
		LOGGER.info("Finishing process to load source");
	}

}
