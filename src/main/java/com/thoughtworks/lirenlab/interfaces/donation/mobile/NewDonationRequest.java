package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class NewDonationRequest {

    private List<BookDTO> books;

    public NewDonationRequest() {
        books = new ArrayList<BookDTO>();
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public void addBook(BookDTO book) {
        this.books.add(book);
    }
}
