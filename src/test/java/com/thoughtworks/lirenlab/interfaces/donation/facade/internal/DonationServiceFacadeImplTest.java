package com.thoughtworks.lirenlab.interfaces.donation.facade.internal;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;
import static com.thoughtworks.lirenlab.domain.model.donation.Book.newBook;
import static com.thoughtworks.lirenlab.domain.model.donation.DonationId.donationId;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
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
}
