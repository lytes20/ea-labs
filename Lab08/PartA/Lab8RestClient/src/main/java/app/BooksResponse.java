package app;

import java.util.List;

public class BooksResponse {
    private List<Book> books;

    public BooksResponse() {
    }

    public BooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}