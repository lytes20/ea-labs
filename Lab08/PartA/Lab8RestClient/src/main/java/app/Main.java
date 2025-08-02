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

        Book book = new Book("1000010", "Mohammed", "Java for Dummies", 50.00);
        Book book1 = new Book("1000000", "Gideon", "Advanced Java", 50.00);
//         Add book
         Book newBook = restClient.post().uri("")
         .contentType(MediaType.APPLICATION_JSON)
         .body(book)
         .retrieve()
         .body(Book.class);
         System.out.println(newBook);

        BooksResponse booksResponse = restClient.get()
                .uri("")
                .retrieve()
                .body(BooksResponse.class);
        System.out.println(booksResponse.getBooks());

        Book lastBook =  restClient.get()
                .uri("/{isbn}", "1000010")
                .retrieve()
                .body(Book.class);
        System.out.println(lastBook);

        // Updating a book
        book.setAuthor("Moges");
        restClient.put()
                .uri("/{isbn}", "1000010")
                .contentType(MediaType.APPLICATION_JSON)
                .body(book)
                .retrieve()
                .body(Book.class);

        // Fetch the updated book
        Book updatedBook =  restClient.get()
                .uri("/{isbn}", "1000010")
                .retrieve()
                .body(Book.class);
        System.out.println(updatedBook);

        // Search for a book by author
        BooksResponse booksByAuthor =  restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("author", "Moges")
                        .build()
                )
                .retrieve()
                .body(BooksResponse.class);
        System.out.println(booksByAuthor);


//        restClient.delete()
//                .uri("/{isbn}", "1000010")
//                .retrieve()
//                .toBodilessEntity();

        // Fetch the updated book
//        Book deletedBook =  restClient.get()
//                .uri("/{isbn}", "1000010")
//                .retrieve()
//                .body(Book.class);
//        System.out.println(deletedBook);

    }
}
