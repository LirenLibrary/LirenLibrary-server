package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import org.codehaus.jackson.annotate.JsonProperty;

public class UpdatePostSpecificationRequest {

    @JsonProperty("specification")
    private String specification;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
