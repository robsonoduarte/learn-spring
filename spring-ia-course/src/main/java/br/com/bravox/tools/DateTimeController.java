package br.com.bravox.tools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/tools")
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/tomorrow")
    public String tomorrow(){
        return chatClient.prompt()
                .user("What is tomorrow's date")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    @GetMapping("/alarm")
    public String alarm(){
        return chatClient.prompt()
                .user("Can you set an alarm 10 minutes from now? and show the alarm date time")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
