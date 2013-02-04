package com.thoughtworks.lirenlab.infrastructure.apns;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.service.PushService;
import com.thoughtworks.lirenlab.utils.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

import static com.thoughtworks.lirenlab.domain.model.device.Device.device;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApnsPushServiceTest {

    private Device actualDevice;
    private String actualMessage;
    private Properties apnsProps;
    private PushService pushService;
    private DeviceRepository deviceRepository;

    @Before
    public void setUp() throws Exception {
        apnsProps = new Properties();
        apnsProps.load(new ClassPathResource("apns.properties").getInputStream());
        deviceRepository = mock(DeviceRepository.class);
        StubApnsPushService stubApnsPushService = new StubApnsPushService(deviceRepository);
        stubApnsPushService.setMessageDonationApproved(apnsProps.getProperty("apns.message.donation.approved"));
        stubApnsPushService.setMessageDonationRejected(apnsProps.getProperty("apns.message.donation.rejected"));
        stubApnsPushService.setMessageDonationReceived(apnsProps.getProperty("apns.message.donation.received"));
        pushService = stubApnsPushService;
    }

    @Test
    public void can_notify_donation_approved() throws Exception {
        Donation donation = Fixtures.loadDonation("approved");
        Device device = device(donation.deviceId(), deviceToken("abc"));
        when(deviceRepository.find(donation.deviceId())).thenReturn(device);
        pushService.notifyDonationApproved(donation);
        assertThat(actualDevice, is(device));
        assertThat(actualMessage, is(apnsProps.getProperty("apns.message.donation.approved")));
    }


    @Test
    public void can_notify_donation_rejected() throws Exception {
        Donation donation = Fixtures.loadDonation("rejected");
        Device device = device(donation.deviceId(), deviceToken("abc"));
        when(deviceRepository.find(donation.deviceId())).thenReturn(device);
        pushService.notifyDonationRejected(donation);
        assertThat(actualDevice, is(device));
        assertThat(actualMessage, is(apnsProps.getProperty("apns.message.donation.rejected")));
    }

    @Test
    public void can_notify_donation_received() throws Exception {
        Donation donation = Fixtures.loadDonation("approved");
        Device device = device(donation.deviceId(), deviceToken("abc"));
        when(deviceRepository.find(donation.deviceId())).thenReturn(device);
        pushService.notifyDonationReceived(donation);
        assertThat(actualDevice, is(device));
        assertThat(actualMessage, is(apnsProps.getProperty("apns.message.donation.received")));
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
