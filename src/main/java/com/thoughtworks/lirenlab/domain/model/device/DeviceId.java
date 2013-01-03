package com.thoughtworks.lirenlab.domain.model.device;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeviceId implements Serializable {

    private String value;

    public DeviceId(String value) {
        this.value = value;
    }

    public static DeviceId deviceId(String value) {
        return new DeviceId(value);
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
