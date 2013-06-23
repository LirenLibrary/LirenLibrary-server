package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.hibernate.Query;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.utils.Fixtures.loadDonation;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DonationRepositoryHibernateTest extends RepositoryTestBase {

    private DonationRepository donationRepository;
    private Query query;
    private Donation donation;


    @Override
    public void moreSetUp() {
        query = mock(Query.class);
        DonationRepositoryHibernate donationRepositoryHibernate = new DonationRepositoryHibernate();
        donationRepositoryHibernate.setSessionFactory(sessionFactory);
        donationRepository = donationRepositoryHibernate;
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
        given(session.createQuery("from Donation d where d.status = :status order by d.createdDate asc")).willReturn(query);
        given(query.setParameter("status", DonationStatus.NEW)).willReturn(query);
        given(query.list()).willReturn(newArrayList(donation));

        List<Donation> actual = donationRepository.newDonations();

        assertThat(actual, hasItem(donation));
    }

    @Test
    public void should_find_by_id() throws Exception {
        given(session.createQuery("from Donation d where d.id = :id")).willReturn(query);
        given(query.setParameter("id", donation.id().longValue())).willReturn(query);
        given(query.uniqueResult()).willReturn(donation);

        Donation actual = donationRepository.find(donation.id());

        assertThat(actual.id(), is(donation.id()));
    }

    @Test
    public void should_throw_exception_if_donation_not_existed() throws Exception {
        given(session.createQuery("from Donation d where d.id = :id")).willReturn(query);
        given(query.setParameter("id", donation.id().longValue())).willReturn(query);
        given(query.uniqueResult()).willReturn(null);
        try {
            donationRepository.find(donation.id());
            fail("should throw DonationNotFoundException");
        } catch (DonationNotFoundException e) {
            assertThat(e.donationId(), is(donation.id()));
        }
    }

    @Test
    public void should_find_by_device_id() throws Exception {
        given(session.createQuery("from Donation d where d.deviceId = :deviceId order by d.createdDate asc")).willReturn(query);
        given(query.setParameter("deviceId", donation.deviceId())).willReturn(query);
        given(query.list()).willReturn(newArrayList(donation));

        List<Donation> donations = donationRepository.find(donation.deviceId());

        assertThat(donations, hasItem(donation));
    }
}
