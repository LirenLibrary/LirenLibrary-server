package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.domain.model.donation.DonationStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.utils.Fixtures.loadDonation;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DonationRepositoryHibernateTest {

    private DonationRepository donationRepository;
    private SessionFactory sessionFactory;
    private Session session;
    private Query query;
    private Donation donation;


    @Before
    public void setUp() throws Exception {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        query = mock(Query.class);
        DonationRepositoryHibernate donationRepositoryHibernate = new DonationRepositoryHibernate();
        donationRepositoryHibernate.setSessionFactory(sessionFactory);
        donationRepository = donationRepositoryHibernate;
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        donation = loadDonation("id_1");
    }

    @Test
    public void should_save_donation_return_donation_id() throws Exception {
        DonationId actualDonationId = donationRepository.save(donation);
        assertThat(actualDonationId, is(donation.id()));

        verify(session).saveOrUpdate(donation);
    }

    @Test
    public void should_return_new_donations() throws Exception {

        when(session.createQuery("from Donation d where d.status = :status order by d.createdDate asc")).thenReturn(query);
        when(query.setParameter("status", DonationStatus.NEW)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList(donation));

        List<Donation> actual = donationRepository.newDonations();

        assertThat(actual, hasItem(donation));
    }

    @Test
    public void should_find_by_id() throws Exception {
        when(session.createQuery("from Donation d where d.id = :id")).thenReturn(query);
        when(query.setParameter("id", donation.id().longValue())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(donation);

        Donation actual = donationRepository.find(donation.id());

        assertThat(actual.id(), is(donation.id()));
    }

    @Test
    public void should_find_by_device_id() throws Exception {
        when(session.createQuery("from Donation d where d.deviceId = :deviceId order by d.createdDate asc")).thenReturn(query);
        when(query.setParameter("deviceId", donation.deviceId())).thenReturn(query);
        when(query.list()).thenReturn(newArrayList(donation));

        List<Donation> donations = donationRepository.find(donation.deviceId());

        assertThat(donations, hasItem(donation));
    }
}
