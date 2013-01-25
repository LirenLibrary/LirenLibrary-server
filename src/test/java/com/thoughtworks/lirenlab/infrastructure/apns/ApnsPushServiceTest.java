package com.thoughtworks.lirenlab.infrastructure.apns;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.service.PushService;
import org.junit.Test;

import static com.thoughtworks.lirenlab.domain.model.device.Device.device;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApnsPushServiceTest {

    private boolean doPushed = false;
    private Device actualDevice;
    private String actualMessage;


    @Test
    public void can_push_message() throws Exception {
        doPushed = false;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        PushService apnsPushService = new StubApnsPushService(deviceRepository);

        Device device = device(deviceId("123"), deviceToken("abc"));
        String message = "message";
        when(deviceRepository.find(deviceId("123"))).thenReturn(device);
        apnsPushService.push(deviceId("123"), message);
        assertThat(actualDevice, is(device));
        assertThat(actualMessage, is(message));

    }


    private class StubApnsPushService extends ApnsPushService {

        public StubApnsPushService(final DeviceRepository deviceRepository) {
            super(deviceRepository);
        }

        @Override
        protected void doPush(String aMessage, Device aDevice) {
            actualDevice = aDevice;
            actualMessage = aMessage;
        }
    }
}
