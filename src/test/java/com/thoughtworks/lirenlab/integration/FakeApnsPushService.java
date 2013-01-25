package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.service.PushService;

public class FakeApnsPushService implements PushService {

    @Override
    public void push(DeviceId deviceId, String message) {
        //do nothing
    }
}
