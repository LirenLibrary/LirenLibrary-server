package com.thoughtworks.lirenlab.domain.model.device;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeviceToken implements Serializable {

    private String token;

    private DeviceToken(String token) {
        this.token = token;
    }


    /**
     * Required By Hibernate
     */
    public DeviceToken() {
    }

    public static DeviceToken deviceToken(String token) {
        return new DeviceToken(token);
    }

    public String strValue() {
        return token;
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
