package com.thoughtworks.lirenlab.domain.model.donation;

import javax.persistence.*;
import java.io.Serializable;

import static com.thoughtworks.lirenlab.domain.model.donation.BookStatus.*;

@Embeddable
public class Book implements Serializable {

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;

    @Column(name = "title")
    private String title;


    public Book(String isbn, String title, BookStatus status) {
        this.isbn = isbn;
        this.status = status;
        this.title = title;
    }

    /**
     * Required by hibernate
     */
    public Book() {
    }

    public String isbn() {
        return isbn;
    }

    public BookStatus status() {
        return status;
    }

    public String title() {
        return title;
    }

    public static Book approvedBook(String isbn, String title) {
        return new Book(isbn, title, APPROVED);
    }

    public static Book newBook(String isbn, String title) {
        return new Book(isbn, title, APPROVED);
    }

    public static Book rejectedBook(String isbn, String title) {
        return new Book(isbn, title, REJECTED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!isbn.equals(book.isbn)) return false;
        if (status != book.status) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }
}
