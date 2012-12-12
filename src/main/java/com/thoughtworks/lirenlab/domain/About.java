package com.thoughtworks.lirenlab.domain;

import javax.persistence.*;

/**
 * User: zhengli
 * Date: 12/5/12
 */
@Entity
@Table(name="abouts")
public class About {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="message")
    private String message;

    /**
     * required for hibernate
     */
    public About() {
    }

    public About(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
