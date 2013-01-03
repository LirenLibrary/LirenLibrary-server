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


    public Book(String isbn, BookStatus status) {
        this.isbn = isbn;
        this.status = status;
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

    public static Book approvedBook(String isbn) {
        return new Book(isbn, APPROVED);
    }

    public static Book newBook(String isbn) {
        return new Book(isbn, NEW);
    }

    public static Book rejectedBook(String isbn) {
        return new Book(isbn, REJECTED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!isbn.equals(book.isbn)) return false;
        if (status != book.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
