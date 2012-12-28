package com.thoughtworks.lirenlab.application;

import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.application.impl.DonationServiceImpl;
import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: zhengli
 * Date: 12/27/12
 */
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
        DonationId donationId = new DonationId("12345");
        DeviceId deviceId = new DeviceId("iPhone4-1234");
        List<Book> books = Lists.newArrayList();

        Donation donation = new Donation(deviceId, books);
        when(donationFactory.createDonation(deviceId, books)).thenReturn(donation);
        when(donationRepository.save(donation)).thenReturn(donationId);

        DonationId actualDonationId = donationService.requestDonation(deviceId, books);

        assertThat(actualDonationId, is(equalTo(donationId)));
    }

}
