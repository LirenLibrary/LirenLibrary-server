package com.thoughtworks.lirenlab.interfaces.library.facade.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class LibraryDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("address")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
