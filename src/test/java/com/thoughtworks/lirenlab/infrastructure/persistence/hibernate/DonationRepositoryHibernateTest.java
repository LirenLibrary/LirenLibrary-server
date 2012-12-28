package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DonationRepositoryHibernateTest {

    private DonationRepository donationRepository;
    private SessionFactory sessionFactory;
    private Session session;
    private Query query;


    @Before
    public void setUp() throws Exception {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        query = mock(Query.class);
        DonationRepositoryHibernate donationRepositoryHibernate = new DonationRepositoryHibernate();
        donationRepositoryHibernate.setSessionFactory(sessionFactory);
        donationRepository = donationRepositoryHibernate;
    }

    @Test
    public void should_save_donation_return_donation_id() throws Exception {
        Donation donation = mock(Donation.class);

        when(donation.id()).thenReturn(new DonationId("12345"));
        when(sessionFactory.getCurrentSession()).thenReturn(session);

        DonationId actualDonationId = donationRepository.save(donation);
        assertThat(actualDonationId, is(equalTo(new DonationId("12345"))));

        verify(session).saveOrUpdate(donation);
    }

    @Test
    public void should_return_new_donations() throws Exception {

        DeviceId deviceId = new DeviceId("iPhone4-1234");
        List<Book> books = newArrayList();
        Donation donation = new Donation(deviceId, books);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("from Donation d where d.status = :status order by d.createdDate asc")).thenReturn(query);
        when(query.setParameter("status", DonationStatus.NEW)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList(donation));

        List<Donation> actual = donationRepository.newDonations();

        assertThat(actual, hasItem(donation));
    }


}
