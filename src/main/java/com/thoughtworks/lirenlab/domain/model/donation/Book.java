package com.thoughtworks.lirenlab.domain.model.donation;

public class Book {
    private String ISBN;
    private String status;

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
}
