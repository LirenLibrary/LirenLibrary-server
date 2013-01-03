package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.application.impl.DeviceServiceImpl;
import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DeviceServiceTest {

    private DeviceRepository deviceRepository;
    private DeviceService deviceService;

    @Before
    public void setUp() throws Exception {
        deviceRepository = mock(DeviceRepository.class);
        deviceService = new DeviceServiceImpl(deviceRepository);
    }

    @Test
    public void should_register_device_when_device_not_existed() throws Exception {
        deviceService.register(deviceId("12234"), deviceToken("token"));
        verify(deviceRepository).save(Matchers.isA(Device.class));
    }

    @Test
    public void should_update_push_token_when_device_existed() throws Exception {
        //Given
        Device device = Device.device(deviceId("12234"), deviceToken("token"));
        when(deviceRepository.find(deviceId("12234"))).thenReturn(device);

        //when
        deviceService.register(deviceId("12234"), deviceToken("tokenToUpdate"));

        //then
        verify(deviceRepository).update(device);
        assertThat(device.token(), is(equalTo(deviceToken("tokenToUpdate"))));
    }


}
