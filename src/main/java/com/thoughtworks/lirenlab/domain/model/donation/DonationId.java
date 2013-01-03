package com.thoughtworks.lirenlab.domain.model.donation;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DonationId implements Serializable {

    private String id;

    private DonationId(String id) {
        this.id = id;
    }

    public static DonationId donationId(String id) {
        return new DonationId(id);
    }

    public String strValue() {
        return id;
    }

    @Override
    public String toString() {
        return "DonationId{" +
                "value=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonationId that = (DonationId) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Required by hibernate
     */
    protected DonationId(){

    }
}
