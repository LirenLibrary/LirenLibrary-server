package com.thoughtworks.lirenlab.interfaces.device.facade.internal;

import com.thoughtworks.lirenlab.application.DeviceService;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import com.thoughtworks.lirenlab.interfaces.device.facade.DeviceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceServiceFacadeImpl implements DeviceServiceFacade {

    private final DeviceService deviceService;

    @Autowired
    public DeviceServiceFacadeImpl(final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public void registerDevice(String deviceId, String deviceToken) {
        this.deviceService.registerPushToken(DeviceId.deviceId(deviceId), new DeviceToken(deviceToken));
    }
}
