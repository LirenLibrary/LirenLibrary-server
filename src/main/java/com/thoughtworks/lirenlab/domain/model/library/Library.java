package com.thoughtworks.lirenlab.domain.model.library;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "libraries")
@Access(AccessType.FIELD)
public class Library {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "contacter")
    private String contacter;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Library(String name, String contacter, String address, String postcode, String telphone) {
        this.name = name;
        this.contacter = contacter;
        this.address = address;
        this.postCode = postcode;
        this.telephone = telphone;
    }


    public String address(){
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Library(Long id, String address) {
        this.id = id;
        this.address = address;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    /*for hibernate*/
    public Library() {
    }

    public Long id() {
        return id;
    }

    public void setUpdatedDate() {
        this.updatedDate = new Date();
    }
}
