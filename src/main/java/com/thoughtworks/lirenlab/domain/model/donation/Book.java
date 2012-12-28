package com.thoughtworks.lirenlab.domain.model.donation;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Book implements Serializable {
    private String ISBN;
    private String status;


    public Book(String ISBN, String status) {
        this.ISBN = ISBN;
        this.status = status;
    }

    /**
     * Required by hibernate
     */
    public Book() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!ISBN.equals(book.ISBN)) return false;
        if (!status.equals(book.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ISBN.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
