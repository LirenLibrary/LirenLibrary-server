package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.application.impl.DonationServiceImpl;
import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Donation.donation;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationId.donationId;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DonationServiceTest {

    private DonationRepository donationRepository;
    private DonationFactory donationFactory;
    private DonationService donationService;

    @Before
    public void setUp() throws Exception {
        donationRepository = mock(DonationRepository.class);
        donationFactory = mock(DonationFactory.class);
        donationService = new DonationServiceImpl(donationRepository, donationFactory);
    }

    @Test
    public void should_request_donation_return_donation_id() throws Exception {
        List<Book> books = newArrayList(newBook("isbn", "title"));
        Donation donation = donation(deviceId("iPhone4-1234"), books);
        when(donationFactory.createDonation(deviceId("iPhone4-1234"), books)).thenReturn(donation);
        when(donationRepository.save(donation)).thenReturn(donationId("12345"));

        DonationId actualDonationId = donationService.newDonation(deviceId("iPhone4-1234"), books);

        assertThat(actualDonationId, is(equalTo(donationId("12345"))));
    }

}
