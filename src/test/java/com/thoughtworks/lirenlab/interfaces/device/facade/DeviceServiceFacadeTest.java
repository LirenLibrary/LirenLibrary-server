package com.thoughtworks.lirenlab.interfaces.device.facade;

import com.thoughtworks.lirenlab.application.DeviceService;
import com.thoughtworks.lirenlab.interfaces.device.facade.internal.DeviceServiceFacadeImpl;
import org.junit.Test;

import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeviceServiceFacadeTest {

    @Test
    public void can_register_device() throws Exception {
        DeviceService deviceService = mock(DeviceService.class);
        DeviceServiceFacade deviceServiceFacade = new DeviceServiceFacadeImpl(deviceService);
        deviceServiceFacade.registerDevice("iphone5", "iphone5LTE124");

        verify(deviceService).register(deviceId("iphone5"), deviceToken("iphone5LTE124"));
    }
}