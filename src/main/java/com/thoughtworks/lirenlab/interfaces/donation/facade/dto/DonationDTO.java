package com.thoughtworks.lirenlab.interfaces.donation.facade.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DonationDTO {

    @JsonProperty("donation_id")
    private String id;

    @JsonProperty("donation_status")
    private String status;

    @JsonProperty("donation_time")
    private Long createdTimeStamp;

    @JsonProperty("donation_item_count")
    private Integer bookAmount;

    @JsonProperty("books")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
