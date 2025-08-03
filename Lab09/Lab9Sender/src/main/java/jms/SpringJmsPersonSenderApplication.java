package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.ConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Optional;

@SpringBootApplication
@EnableJms
public class SpringJmsPersonSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsPersonSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
//		Person person = new Person("Frank", "Brown");
		// convert person to JSON string
//		ObjectMapper objectMapper = new ObjectMapper();
//		String personAsString = objectMapper.writeValueAsString(person);
//
//		System.out.println("Sending a JMS message to testTopic:" + personAsString);
//		jmsTemplate.convertAndSend("testTopic", personAsString);

		sendCommand("+", 7);
		sendCommand("+", 8);
		sendCommand("*", 2);
		sendCommand("-", 3);

	}


	private void sendCommand(String operator, int value) {
		Command command = new Command(operator, value);
		ObjectMapper objectMapper = new ObjectMapper();
        String commandAsString = null;
        try {
            commandAsString = objectMapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sending command: " + operator + " " + value);
		jmsTemplate.convertAndSend("calcTopic", commandAsString);
	}

}
