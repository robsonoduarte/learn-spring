package br.com.bravox.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG;

@RestController
public class ImageDetection {
    private final ChatClient chatClient;

    @Value("classpath:images/driver_truck_tired.jpeg")
    Resource image;

    public ImageDetection(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("image-to-text")
    public String image() {
        return chatClient.prompt()
                .user(u -> {
                    u.text("Can you please explain what you see in the following image?")
                            .media(IMAGE_JPEG, image);
                }).call()
                .content();
    }
}