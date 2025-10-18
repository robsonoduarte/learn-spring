package br.com.bravox.multimodal.image;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageGeneration {

    private final OpenAiImageModel imageModel;

    public ImageGeneration(OpenAiImageModel openAiImageModel) {
        this.imageModel = openAiImageModel;
    }

    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImane(
            @RequestParam(defaultValue = "A beautiful sunset over mountains") String prompt) {

        var options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();

        var response = imageModel.call(new ImagePrompt(prompt, options));
        var imageUrl = response.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                "prompt", prompt,
                "imageUrl", imageUrl
        ));
    }
}
