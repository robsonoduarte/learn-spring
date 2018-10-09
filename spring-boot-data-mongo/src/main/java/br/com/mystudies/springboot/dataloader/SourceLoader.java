package br.com.mystudies.springboot.dataloader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SourceLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SourceLoader.class);

	private static final String SOURCE_URL = "http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/source-2";


	public List<String> loadLines() throws Exception {

		LOGGER.info("Starting loading the source by HTTP");

		List<String> lines =
				new BufferedReader(new InputStreamReader(new URL(SOURCE_URL).openStream(),UTF_8))
				.lines()
				.collect(toList());

		LOGGER.info("Finishing loading of source by HTTP");

		return lines;
	}

}
