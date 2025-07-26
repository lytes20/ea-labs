package app;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.ProductRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class OrderApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Product product = new Product();
        product.setName("Hibernate 4");
        product.setDescription("Good book on Hibernate");
        product.setPrice(35.50);
        productRepository.save(product);

    }
}
