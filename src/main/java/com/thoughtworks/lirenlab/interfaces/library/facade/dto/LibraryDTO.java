package com.thoughtworks.lirenlab.interfaces.library.facade.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class LibraryDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contacter")
    private String contacter;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("telephone")
    private String telephone;

    public LibraryDTO() {
    }

    public String getName() {
        return name;
    }

    public LibraryDTO(String address, String name, String contacter, String postcode, String telphone) {
        this.address = address;
        this.name = name;
        this.contacter = contacter;
        this.postcode = postcode;
        this.telephone = telphone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

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

    @Override
    public String toString() {
        return "id: " + getId() + "; name: " + getName() + "; address: " + getAddress() +
                "; contacter: " + getContacter() + "; postcode: " + getPostcode() +
                "; telephone: " + getTelephone() + ";";
    }
}
