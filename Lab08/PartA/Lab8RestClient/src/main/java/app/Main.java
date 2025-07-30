package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8080/books").build();

//         Add book
         Book newBook = restClient.post().uri("")
         .contentType(MediaType.APPLICATION_JSON)
         .body(new Book("1000001", "Gideon", "Advanced Java", 50.00))
         .retrieve()
         .body(Book.class);
         System.out.println(newBook);

        BooksResponse booksResponse = restClient.get()
                .uri("")
                .retrieve()
                .body(BooksResponse.class);
        System.out.println(booksResponse.getBooks());



    }
}
