package com.thoughtworks.lirenlab.interfaces.donation.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class DonationDTO {

    private String id;
    private Long createdTimeStamp;
    private String createdDate;
    private Integer bookAmount;
    private List<BookDTO> books;

    public DonationDTO() {
        books = new ArrayList<BookDTO>();
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }

    public Long getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Long createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
