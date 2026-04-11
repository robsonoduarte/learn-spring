package br.bravox.spring_ia_mcp_stdio;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionsTools {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SessionsTools.class);
    private List<Session> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper;

    public SessionsTools(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Tool(name = "spring-io-sessions", description = "Load sessions from Spring I/O 2025")
    public List<Session> findAllSessions() {
        return sessions;
    }

    @PostConstruct
    private void init(){
        LOGGER.info("Loading sessions from Spring I/O 2025");
        try(var inputStream = TypeReference.class.getResourceAsStream("/sessions.json")) {
            var conference = objectMapper.readValue(inputStream, Conference.class);
            this.sessions = conference.sessions();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sessions from Spring I/O 2025");
        }
    }
}
