package edu.miu.ChatPrompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private final ChatClient chatClient;

    public SimpleController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "question")String question) {
        return chatClient.prompt()
                .system("Your function is to answer questions about pet healthcare")
                .user(question)
                .call()
                .content();
    }
}

