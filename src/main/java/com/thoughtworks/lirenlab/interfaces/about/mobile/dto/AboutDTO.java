package com.thoughtworks.lirenlab.interfaces.about.mobile.dto;

/**
 * User: zhengli
 * Date: 12/5/12
 */
public class AboutDTO {

    private String name;
    private String message;

    public AboutDTO(String name, String message) {
        this.name = name;
        this.message = message;
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
