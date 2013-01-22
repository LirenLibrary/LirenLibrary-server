package com.thoughtworks.lirenlab.domain.model.device;

import org.junit.Test;

import static com.thoughtworks.lirenlab.domain.model.device.Device.device;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;

public class DeviceTest {

    @Test(expected = IllegalArgumentException.class)
    public void device_id_is_required() throws Exception {
        device(null, deviceToken("some token"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void device_token_is_required() throws Exception {
        device(deviceId("id"), null);
    }
}
