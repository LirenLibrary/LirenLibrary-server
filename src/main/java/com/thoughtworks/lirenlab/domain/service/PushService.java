package com.thoughtworks.lirenlab.domain.service;

import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

public interface PushService {

    public void push(DeviceId deviceId, String message);
}
