package bravox.dev;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/short")
    public String shortPrompt(){
        return chatClient.prompt()
                .user("When did the first human walk on the moon?")
                .call()
                .content();
    }

    @GetMapping("/long")
    public String longPrompt(){
        return chatClient.prompt()
                .user("Write me a 1000 words story about a robot")
                .call()
                .content();
    }

}
