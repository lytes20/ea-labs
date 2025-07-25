package edu.miu.Lab4PartA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


public class BookApp implements CommandLineRunner {

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

//    public static void main(String[] args){
//        SpringApplication.run(BookApp.class, args);
//    }

    @Override
    public void run(String... args) throws Exception {
        Publisher pub = new Publisher("Gwen");
        publisherRepository.save(pub);



        Book book = new Book("KEYS", "We the Best", "DJ Khaled", pub);
        Book book1 = new Book("EASY", "Enterprise Ar", "Rene", null);
        bookRepository.save(book);
        bookRepository.save(book1);

        bookRepository.findAll().forEach(System.out::println);

    }
}
