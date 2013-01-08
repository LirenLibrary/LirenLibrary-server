package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.application.impl.DonationServiceImpl;
import com.thoughtworks.lirenlab.domain.model.donation.*;
import com.thoughtworks.lirenlab.utils.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.approvedBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.rejectedBook;
import static com.thoughtworks.lirenlab.domain.model.donation.Donation.donation;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationId.donationId;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

    @Test
    public void should_approve_specified_book_of_donation() throws Exception {

        String isbn="isbn1";

        Donation donation = Fixtures.loadDonation("id_1");
        when(donationRepository.find(donationId("1"))).thenReturn(donation);

        donationService.approveBook(donationId("1"), isbn);

        assertThat(donation.books(), hasItem(approvedBook(isbn, "title1")));
        assertThat(donation.books(), not(hasItem(rejectedBook(isbn, "title1"))));
        verify(donationRepository).save(donation);
    }

    @Test
    public void should_reject_specified_book_of_donation() throws Exception {

        String isbn="isbn2";

        Donation donation = Fixtures.loadDonation("id_1");
        when(donationRepository.find(donationId("1"))).thenReturn(donation);

        donationService.rejectBook(donationId("1"), isbn);

        assertThat(donation.books(), hasItem(rejectedBook(isbn, "title2")));
        assertThat(donation.books(), not(hasItem(approvedBook(isbn, "title2"))));
        verify(donationRepository).save(donation);
    }
}
