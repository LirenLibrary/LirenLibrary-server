package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.application.DonationService;
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

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.nullValue;
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

    @Test
    public void should_request_donation() throws Exception {
        DonationId donationId = donationService.newDonation(deviceId("12345"), newArrayList(newBook("isbn12345", "title")));
        Donation actual = donationById(donationId);
        assertThat(actual, not(nullValue()));
        assertThat(actual.books(), hasItem(newBook("isbn12345", "title")));
    }

    private Donation donationById(DonationId donationId) {
        return (Donation) sessionFactory.getCurrentSession().createCriteria(Donation.class)
                    .add(Property.forName("id").eq(Long.valueOf(donationId.strValue()))).uniqueResult();
    }
}
