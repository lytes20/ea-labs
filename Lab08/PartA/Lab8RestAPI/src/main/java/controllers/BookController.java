package controllers;

import domain.Book;
import domain.Books;
import domain.DuplicateIsbnException;
import org.springframework.http.HttpStatus;
import repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        // Check if book with this ISBN already exists
        if (bookRepository.existsById(book.getIsbn())) {
            throw new DuplicateIsbnException("Book with ISBN '" + book.getIsbn() + "' already exists");
        }

        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn,  @RequestBody Book updatedBook) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isPresent()) {
            Book existingBook = book.get();
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPrice(updatedBook.getPrice());
            Book savedBook = bookRepository.save(existingBook);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books")
    public ResponseEntity<Books> getAllBooks() {
        Books books = new Books(bookRepository.findAll());
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

    @GetMapping("/books/search")
    public ResponseEntity<Books> searchBooks(@RequestParam String author) {
        Books books = new Books(bookRepository.findByAuthor(author));
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }


    // Exception per controller
    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<String> handleDuplicateIsbnException(DuplicateIsbnException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
