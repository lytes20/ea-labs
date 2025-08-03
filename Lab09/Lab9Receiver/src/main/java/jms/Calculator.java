package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    private int total = 0;

    @JmsListener(destination = "calcTopic")
    public void receiveCommand(final String commandAsString) {

        ObjectMapper objectMapper = new ObjectMapper();
        Command command = null;
        try {
            command = objectMapper.readValue(commandAsString, Command.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        switch (command.getOperator()) {
            case "+":
                total += command.getValue();
                break;
            case "-":
                total -= command.getValue();
                break;
            case "*":
                total *= command.getValue();
                break;
            default:
                System.out.println("Unknown operator: " + command.getOperator());
                return;
        }

        System.out.println("Calculator Receiver, command: " + command.getOperator() + " " + command.getValue() +
                " -> Total now: " + total);
    }
}
