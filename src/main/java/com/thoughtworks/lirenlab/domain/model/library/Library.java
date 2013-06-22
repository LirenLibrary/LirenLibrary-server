package com.thoughtworks.lirenlab.domain.model.library;

import javax.persistence.*;

@Entity
@Table(name = "libraries")
@Access(AccessType.FIELD)
public class Library {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "address")
    private String address;


    public String address(){
        return address;
    }


    public Library(String address) {
        this.address = address;
    }

    /*for hibernate*/
    protected Library() {
    }

    public Long id() {
        return id;
    }
}
