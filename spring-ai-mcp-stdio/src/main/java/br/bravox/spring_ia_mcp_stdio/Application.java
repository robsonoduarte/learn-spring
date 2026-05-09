package br.bravox.spring_ia_mcp_stdio;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public List<ToolCallback> springIoSessionsTools(SessionsTools sessionsTools) {
		return List.of(ToolCallbacks.from(sessionsTools));
	}
}
