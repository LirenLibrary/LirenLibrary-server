package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.application.DeviceService;
import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void register(DeviceId deviceId, DeviceToken pushToken) {
        Device device = this.deviceRepository.find(deviceId);
        if (device != null) {
            device.updateToken(pushToken);
            deviceRepository.update(device);
            return;
        }
        this.deviceRepository.save(Device.device(deviceId, pushToken));
    }
}
