package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import org.junit.Test;

import static com.thoughtworks.lirenlab.domain.model.device.Device.device;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceToken.deviceToken;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DeviceRepositoryHibernateTest extends RepositoryTestBase {

    private DeviceRepository deviceRepository;

    @Override
    public void moreSetUp() {
        DeviceRepositoryHibernate deviceRepositoryHibernate = new DeviceRepositoryHibernate();
        deviceRepositoryHibernate.setSessionFactory(sessionFactory);
        deviceRepository = deviceRepositoryHibernate;
    }

    @Test
    public void should_save_device() throws Exception {
        //Given
        Device device = device(deviceId("12234"), deviceToken("token"));

        //When
        deviceRepository.save(device);

        //Then
        verify(session).save(device);
    }

    @Test
    public void should_update_device() throws Exception {
        //Given
        Device device = device(deviceId("12234"), deviceToken("token"));

        //When
        deviceRepository.update(device);

        //Then
        verify(session).update(device);
    }

    @Test
    public void should_find_device_by_id() throws Exception {
        //Given
        Device device = device(deviceId("12234"), deviceToken("token"));
        given(session.get(Device.class, deviceId("12234"))).willReturn(device);

        //When
        Device actualDevice = deviceRepository.find(deviceId("12234"));

        //Then
        assertThat(actualDevice, is(equalTo(device)));
    }

}
