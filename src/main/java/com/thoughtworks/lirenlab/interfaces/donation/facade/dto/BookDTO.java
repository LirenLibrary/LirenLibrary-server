package com.thoughtworks.lirenlab.interfaces.donation.facade.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookDTO {

    @JsonProperty("ISBN")
    private String isbn;

    private String status;

    @JsonProperty("title")
    private String title;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
