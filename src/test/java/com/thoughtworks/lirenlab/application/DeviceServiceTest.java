package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.application.impl.DeviceServiceImpl;
import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeviceServiceTest {

    private DeviceRepository deviceRepository;
    private DeviceService deviceService;

    @Before
    public void setUp() throws Exception {
        deviceRepository = mock(DeviceRepository.class);
        deviceService = new DeviceServiceImpl(deviceRepository);
    }

    @Test
    public void should_register_push_token_when_device_not_existed() throws Exception {
        DeviceId deviceId = DeviceId.deviceId("12234");
        DeviceToken pushToken = new DeviceToken("token");

        deviceService.registerPushToken(deviceId, pushToken);

        verify(deviceRepository).save(Matchers.isA(Device.class));
    }

    @Test
    public void should_update_push_token_when_device_existed() throws Exception {
        //Given
        DeviceId deviceId = DeviceId.deviceId("12234");
        DeviceToken pushToken = new DeviceToken("token");
        Device device = new Device(deviceId, pushToken);
        when(deviceRepository.find(deviceId)).thenReturn(device);
        DeviceToken pushTokenToUpdate = new DeviceToken("tokenToUpdate");

        //when
        deviceService.registerPushToken(deviceId, pushTokenToUpdate);

        //then
        verify(deviceRepository).update(device);
        assertThat(device.token(), is(equalTo(pushTokenToUpdate)));
    }


}
