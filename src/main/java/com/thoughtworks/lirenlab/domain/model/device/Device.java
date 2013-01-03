package com.thoughtworks.lirenlab.domain.model.device;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Access(AccessType.FIELD)
public class Device {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private DeviceId id;

    @Column
    @AttributeOverride(name = "token", column = @Column(name = "token"))
    private DeviceToken token;

    private Device(DeviceId id, DeviceToken deviceToken) {
        this.id = id;
        this.token = deviceToken;
    }

    public static Device device(DeviceId id, DeviceToken deviceToken) {
        return new Device(id, deviceToken);
    }

    public DeviceId id() {
        return this.id;
    }

    public DeviceToken token() {
        return this.token;
    }

    public void updateToken(DeviceToken newToken) {
        this.token = newToken;
    }

    /**
     * Required By Hibernate
     */
    protected Device() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (!id.equals(device.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
