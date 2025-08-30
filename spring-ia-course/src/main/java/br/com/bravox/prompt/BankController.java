package br.com.bravox.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final ChatClient chatClient;

    public BankController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @RequestMapping("/chat")

    public String chat(@RequestParam String message) {
        var system = """
            You are customer service assistant for Bank
            You ONLY discuss:
            - Account balances and transactions
            - Branch locations and hours
            - General banking service
            
            if asked about anything else, respond: "I can only help with baking-related questions'
            """;
        return chatClient.prompt()
                .user(message)
                .system(system)
                .call()
                .content();
    }
}
