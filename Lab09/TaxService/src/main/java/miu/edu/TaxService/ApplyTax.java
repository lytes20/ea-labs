package miu.edu.TaxService;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ApplyTax {
    @JmsListener(destination = "tax")
    public void receiveMessage(final String deposit){
        System.out.println("Received deposit " + deposit);
    }
}
