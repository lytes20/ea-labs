package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    private final Logger logger;
    private final EmailSender emailSender;

    @Autowired
    public ProductService(Logger logger, EmailSender emailSender) {
        this.logger = logger;
        this.emailSender = emailSender;
    }

    public void addProduct(Product product) {
        // Simulate saving to database
        logger.log("Saving product to database: " + product.getName());
        System.out.println("Product saved: " + product.getName());

        // Send email notification
        logger.log("Sending email for new product: " + product.getName());
        String emailMessage = "New Product Added: " + product.getName() + " has been added.";
        emailSender.sendEmail("gideonbamuleseyo@gmail.com", emailMessage);
    }
}