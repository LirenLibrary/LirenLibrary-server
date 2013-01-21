package com.thoughtworks.lirenlab.domain.model.donation;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PostSpecification implements Serializable {

    @Column(name = "post_spec")
    private String specification;

    private PostSpecification(String specification) {
        this.specification = specification;
    }

    public static PostSpecification postSpecification(String specification) {
        return new PostSpecification(specification);
    }

    public static PostSpecification emptySpecification() {
        return postSpecification("");
    }

    public String strValue() {
        return specification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostSpecification that = (PostSpecification) o;

        if (!specification.equals(that.specification)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return specification.hashCode();
    }

    /**
     * Required by Hibernate
     */
    protected PostSpecification() {
    }
}
