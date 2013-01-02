package com.thoughtworks.lirenlab.domain.model.device;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeviceToken implements Serializable {

    private String token;

    public DeviceToken(String token) {
       this.token = token;
    }

    /**
     * Required By Hibernate
     */
    public DeviceToken() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceToken pushToken = (DeviceToken) o;

        if (!token.equals(pushToken.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}
