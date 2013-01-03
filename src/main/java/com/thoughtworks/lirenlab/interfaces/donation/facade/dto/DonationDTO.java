package com.thoughtworks.lirenlab.interfaces.donation.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class DonationDTO {

    private String id;
    private String createdDate;
    private Integer bookAmount;

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    private List<BookDTO> books;

    public DonationDTO() {
        books = new ArrayList<BookDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonationDTO that = (DonationDTO) o;

        if (!bookAmount.equals(that.bookAmount)) return false;
        if (!createdDate.equals(that.createdDate)) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + bookAmount.hashCode();
        return result;
    }
}
