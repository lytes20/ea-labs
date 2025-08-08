package edu.miu.ChatPrompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    private ChatClient chatClient;



    @GetMapping("/chat")
    public PetHealthResponse chat(@RequestParam(value = "question") String question) {
        return chatClient.prompt()
                .system("Your function is to answer questions about pet healthcare")
                .user(question)
                .call()
                .entity(PetHealthResponse.class);
    }
}
