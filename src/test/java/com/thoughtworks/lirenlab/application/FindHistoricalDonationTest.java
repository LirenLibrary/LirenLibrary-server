package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.application.impl.DonationServiceImpl;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationFactory;
import com.thoughtworks.lirenlab.domain.model.donation.InvalidHistoricalDonationException;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.domain.service.PushService;
import com.thoughtworks.lirenlab.utils.Fixtures;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindHistoricalDonationTest {

    private DonationRepository donationRepository;
    private DonationFactory donationFactory;
    private DonationService donationService;
    private PushService pushService;

    @Before
    public void setUp() throws Exception {
        donationRepository = mock(DonationRepository.class);
        donationFactory = mock(DonationFactory.class);
        pushService = mock(PushService.class);
        donationService = new DonationServiceImpl(donationRepository, donationFactory, pushService);
    }

    @Test
    public void new_donation_is_invalid_historical_donation() throws Exception {
        Donation donation = Fixtures.loadDonation("new");
        when(donationRepository.find(donation.id())).thenReturn(donation);
        try {
            donationService.findHistorical(donation.id());
            fail("should throw InvalidHistoricalDonationException");
        } catch (InvalidHistoricalDonationException e) {
            assertThat(e.donationId(), is(donation.id()));
        }
    }

    @Test
    public void non_new_donation_is_valid_historical_donation() throws Exception {
        Donation donation = Fixtures.loadDonation("approved");
        when(donationRepository.find(donation.id())).thenReturn(donation);
        Donation historical = donationService.findHistorical(donation.id());
        assertThat(historical, is(donation));
    }
}
