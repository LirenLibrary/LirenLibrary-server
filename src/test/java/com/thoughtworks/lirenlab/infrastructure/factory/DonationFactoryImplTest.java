package com.thoughtworks.lirenlab.infrastructure.factory;

import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DonationFactoryImplTest {

    @Test
    public void should_create_donation() throws Exception {
        DonationFactory donationFactory = new DonationFactoryImpl();
        DeviceId deviceId = DeviceId.deviceId("12345");
        ArrayList<Book> books = newArrayList(Book.newBook("1234", "title"));


        Donation donation = donationFactory.createDonation(deviceId, books);
        assertThat(donation.deviceId(), is(equalTo(deviceId)));
        assertThat(donation.books().size(), is(equalTo(books.size())));
        assertThat(donation.status(), is(equalTo(DonationStatus.NEW)));
    }
}
