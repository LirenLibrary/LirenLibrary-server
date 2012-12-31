package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepositoryHibernate extends HibernateRepository implements DeviceRepository {

    @Override
    public void save(Device device) {
        this.currentSession().save(device);
    }

    @Override
    public Device find(DeviceId deviceId) {
        return (Device) this.currentSession().get(Device.class, deviceId);
    }

    @Override
    public void update(Device device) {
        currentSession().update(device);
    }
}
