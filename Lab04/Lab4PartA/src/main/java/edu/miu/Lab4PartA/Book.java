package edu.miu.Lab4PartA;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    public String ISBN;
    public String title;
    public String author;

    @ManyToOne(optional = true)
    @JoinTable(
            name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    public Publisher publisher;

    protected Book() {
    }

    public Book(String ISBN, String title, String author, Publisher publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
