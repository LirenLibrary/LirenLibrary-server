package com.thoughtworks.lirenlab.interfaces.device.facade;

import com.thoughtworks.lirenlab.application.DeviceService;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import com.thoughtworks.lirenlab.interfaces.device.facade.internal.DeviceServiceFacadeImpl;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeviceServiceFacadeTest {

    @Test
    public void can_register_device() throws Exception {
        DeviceService deviceService = mock(DeviceService.class);
        DeviceServiceFacade deviceServiceFacade = new DeviceServiceFacadeImpl(deviceService);
        deviceServiceFacade.registerDevice("iphone5", "iphone5LTE124");

        verify(deviceService).registerPushToken(DeviceId.deviceId("iphone5"), new DeviceToken("iphone5LTE124"));
    }
}