package com.thoughtworks.lirenlab.interfaces.donation.facade.internal;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationId.donationId;
import static com.thoughtworks.lirenlab.utils.Fixtures.loadDonation;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DonationServiceFacadeImplTest {

    private DonationService donationService;
    private DonationRepository donationRepository;
    private DonationServiceFacadeImpl donationServiceFacade;

    @Before
    public void setUp() throws Exception {
        donationService = mock(DonationService.class);
        donationRepository = mock(DonationRepository.class);
        donationServiceFacade = new DonationServiceFacadeImpl(donationService, donationRepository);
    }

    @Test
    public void can_send_new_donation() throws Exception {
        BookDTO book = new BookDTO();
        book.setIsbn("isbn123");
        book.setTitle("title");
        when(donationService.newDonation(deviceId("iphone5"), newArrayList(newBook("isbn123", "title")))).thenReturn(donationId("12345"));

        String donationId = donationServiceFacade.newDonation("iphone5", newArrayList(book));

        assertThat(donationId, is("12345"));
    }

    @Test
    public void get_donation_by_id() throws Exception {
        Donation donation = loadDonation("id_1");
        when(donationRepository.find(donation.id())).thenReturn(donation);

        DonationDTO result = donationServiceFacade.getDonationById(donation.id().strValue());

        assertThat(result.getId(), is(donation.id().strValue()));
    }

    @Test
    public void get_donations_by_device_id() throws Exception {
        Donation donation = loadDonation("id_1");
        when(donationRepository.find(donation.deviceId())).thenReturn(newArrayList(donation));

        List<DonationDTO> result = donationServiceFacade.getDonationsOfDevice(donation.deviceId().strValue());

        assertThat(result.get(0).getId(), is(donation.id().strValue()));
    }

    @Test
    public void should_approve_book_of_donation_by_isbn() throws Exception {
        donationServiceFacade.approveBook("123", "isbn");
        verify(donationService).approveBook(donationId("123"), "isbn");
    }

    @Test
    public void should_reject_book_of_donation_by_isbn() throws Exception {
        donationServiceFacade.rejectBook("123", "isbn");
        verify(donationService).rejectBook(donationId("123"), "isbn");
    }
}
