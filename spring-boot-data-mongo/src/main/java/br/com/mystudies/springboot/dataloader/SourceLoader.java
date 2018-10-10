package br.com.mystudies.springboot.dataloader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SourceLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SourceLoader.class);

	public List<String> loadLines() throws Exception {

		LOGGER.info("Starting loading the source...");

		List<String> lines =
				new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/source/source"),UTF_8))
				.lines()
				.collect(toList());

		LOGGER.info("Finishing loading the source");

		return lines;
	}

}
