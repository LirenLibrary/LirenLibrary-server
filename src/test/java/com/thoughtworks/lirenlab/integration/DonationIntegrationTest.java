package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DonationIntegrationTest {

    @Autowired
    private DonationService donationService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DonationRepository donationRepository;

    @Test
    public void should_create_donation() throws Exception {
        DonationId donationId = donationService.newDonation(deviceId("12345"), newArrayList(newBook("isbn12345", "title")));
        Donation actual = donationRepository.find(donationId);
        assertThat(actual.deviceId(), is(deviceId("12345")));
        assertThat(actual.books(), hasItem(newBook("isbn12345", "title")));
    }

    @Test
    public void should_find_donations_of_specified_device() throws Exception {
        donationService.newDonation(deviceId("device"), newArrayList(newBook("isbn1", "title")));
        donationService.newDonation(deviceId("anotherDevice"), newArrayList(newBook("isbn12345", "title")));
        donationService.newDonation(deviceId("device"), newArrayList(newBook("isbn2", "title")));
        List<Donation> donations = donationRepository.find(deviceId("device"));
        List<Donation> anotherDonations = donationRepository.find(deviceId("anotherDevice"));

        assertThat(donations.size(), is(2));
        assertThat(anotherDonations.size(), is(1));
    }
}
