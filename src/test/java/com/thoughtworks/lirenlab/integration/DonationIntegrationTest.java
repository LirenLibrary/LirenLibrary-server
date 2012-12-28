package com.thoughtworks.lirenlab.integration;

import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class DonationIntegrationTest {

    @Autowired
    private DonationService donationService;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void should_request_donation() throws Exception {
        Book abc = new Book("ISBN1234", "Approved");
        ArrayList<Book> books = Lists.<Book>newArrayList(abc);
        DeviceId deviceId = new DeviceId("12345");
        DonationId donationId = donationService.requestDonation(deviceId, books);
        Donation actual = donationById(donationId);
        assertThat(actual, not(nullValue()));
        assertThat(actual.books(), hasItem(abc));
    }

    private Donation donationById(DonationId donationId) {
        return (Donation) sessionFactory.getCurrentSession().createCriteria(Donation.class)
                    .add(Property.forName("id").eq(Long.valueOf(donationId.strValue()))).uniqueResult();
    }
}
