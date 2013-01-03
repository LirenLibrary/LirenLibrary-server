package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

public interface DeviceService {

    void register(DeviceId deviceId, DeviceToken pushToken);
}
