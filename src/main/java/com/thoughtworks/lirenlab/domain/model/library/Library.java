package com.thoughtworks.lirenlab.domain.model.library;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "libraries")
@Access(AccessType.FIELD)
public class Library {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "contacter")
    private String contacter;

    @Column(name = "post_code")
    private String postcode;

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
        this.postcode = postcode;
        this.telephone = telphone;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }


    public String address() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Deprecated
    public Library(String address) {
        this.address = address;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    /*for hibernate*/
    public Library() {
    }

    public String getName() {
        return name;
    }

    public String getContacter() {
        return contacter;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUpdatedDate() {
        this.updatedDate = new Date();
    }

    public String getAddress() {
        return address;
    }

    public void update(Library that) {
        this.address = that.address;
        this.contacter = that.contacter;
        this.name = that.name;
        this.telephone = that.telephone;
        this.postcode = that.postcode;
        this.updatedDate = that.updatedDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "; name: " + getName() + "; address: " + getAddress() +
                "; contacter: " + getContacter() + "; postcode: " + getPostcode() +
                "; telephone: " + getTelephone() + ";";
    }
}
