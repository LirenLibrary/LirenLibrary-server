package com.thoughtworks.lirenlab.domain.model.device;

public interface DeviceRepository {

    void save(Device device);

    Device find(DeviceId deviceId);

    void update(Device device);
}
