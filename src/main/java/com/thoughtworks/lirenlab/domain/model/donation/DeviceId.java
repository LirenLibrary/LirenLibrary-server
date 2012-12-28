package com.thoughtworks.lirenlab.domain.model.donation;

import javax.persistence.Embeddable;

@Embeddable
public class DeviceId {

    private String value;

    public DeviceId(String value) {
        this.value = value;
    }


    public String strValue() {
        return value;
    }

    /**
     * Required by hibernate
     */
    public DeviceId() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceId deviceId = (DeviceId) o;

        if (!value.equals(deviceId.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
