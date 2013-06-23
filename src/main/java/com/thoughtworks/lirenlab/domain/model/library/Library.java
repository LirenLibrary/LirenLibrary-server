package com.thoughtworks.lirenlab.domain.model.library;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "libraries")
@Access(AccessType.FIELD)
public class Library {

    @Id
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;


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
