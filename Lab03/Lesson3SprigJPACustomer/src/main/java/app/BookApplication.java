package app;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.BookRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class BookApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.save(new Book("Steve Jobs", "GIDE0001", "Isaac W", 20.00));
        bookRepository.save(new Book("Bags", "GIDE0011", "Gideon B", 20.00));
        bookRepository.save(new Book("Enterprize Applications", "GIDE0010", "Gideon B", 20.00));

        // fetch all books
        System.out.println("Retrieve books:");
        System.out.println("-------------------------------");
        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }
        System.out.println();

        // Update a book by ISBN
        Book bookToUpdate = bookRepository.findByISBN("GIDE0001");
        if (bookToUpdate != null) {
            bookToUpdate.setTitle("Steve Wozniak");
            bookRepository.save(bookToUpdate);
            System.out.println("Updated book: " + bookToUpdate);
        } else {
            System.out.println("Book with ISBN GIDE0001 not found.");
        }

        // Delete a book by ISBN
        Book bookToDelete = bookRepository.findByISBN("GIDE0010");
        if (bookToDelete != null) {
            bookRepository.delete(bookToDelete);
            System.out.println("Deleted book with ISBN GIDE0010");
        } else {
            System.out.println("Book with ISBN GIDE0010 not found.");
        }

        // fetch all books
        System.out.println("Retrieve books:");
        System.out.println("-------------------------------");
        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }
        System.out.println();

    }
}
