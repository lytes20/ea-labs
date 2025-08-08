package edu.miu.ChatPrompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatPromptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatPromptApplication.class, args);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel) {
		ChatClient.Builder builder = ChatClient.builder(chatModel);

		builder.defaultAdvisors(new LoggingAdvisor());
		return builder.build();
	}

}
