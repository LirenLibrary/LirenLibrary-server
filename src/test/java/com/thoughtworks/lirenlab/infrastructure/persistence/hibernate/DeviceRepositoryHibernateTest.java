package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.model.device.DeviceToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeviceRepositoryHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private DeviceRepository deviceRepository;

    @Before
    public void setUp() throws Exception {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        DeviceRepositoryHibernate deviceRepositoryHibernate = new DeviceRepositoryHibernate();
        deviceRepositoryHibernate.setSessionFactory(sessionFactory);
        deviceRepository = deviceRepositoryHibernate;
    }

    @Test
    public void should_save_device() throws Exception {
        //Given
        Device device = device("12234", "token");
        when(sessionFactory.getCurrentSession()).thenReturn(session);

        //When
        deviceRepository.save(device);

        //Then
        verify(session).save(device);
    }

    @Test
    public void should_update_device() throws Exception {
        //Given
        Device device = device("12234", "token");
        when(sessionFactory.getCurrentSession()).thenReturn(session);

        //When
        deviceRepository.update(device);

        //Then
        verify(session).update(device);
    }

    @Test
    public void should_find_device_by_id() throws Exception {
        //Given
        Device device = device("12234", "token");
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(Device.class, new DeviceId("12234"))).thenReturn(device);

        //When
        Device actualDevice = deviceRepository.find(new DeviceId("12234"));

        //Then
        assertThat(actualDevice, is(equalTo(device)));
    }

    private Device device(String id, String token) {
        DeviceId deviceId = new DeviceId(id);
        DeviceToken pushToken = new DeviceToken(token);
        return new Device(deviceId, pushToken);
    }
}
