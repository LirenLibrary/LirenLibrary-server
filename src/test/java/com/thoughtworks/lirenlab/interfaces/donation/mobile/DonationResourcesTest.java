package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DonationResourcesTest {

    public static final String URI_DONATIONS = "http://www.abc.com/donations";
    private DonationServiceFacade donationServiceFacade;
    private DonationResources donationResources;
    private UriInfo uriInfo;

    @Before
    public void setUp() throws Exception {
        uriInfo = mock(UriInfo.class);
        donationServiceFacade = mock(DonationServiceFacade.class);
        donationResources = new DonationResources(donationServiceFacade);
    }

    @Test
    public void create_new_donation() throws Exception {
        NewDonationRequest newDonationRequest = new NewDonationRequest();
        BookDTO book = new BookDTO();
        book.setIsbn("isbn1234");
        book.setTitle("title");
        newDonationRequest.addBook(book);

        String deviceId = "iphone5";
        String donationId = "id123";
        when(donationServiceFacade.newDonation(eq(deviceId), anyListOf(BookDTO.class))).thenReturn(donationId);
        when(uriInfo.getRequestUriBuilder()).thenReturn(UriBuilder.fromPath(URI_DONATIONS));

        Response response = donationResources.newDonation(uriInfo, deviceId, newDonationRequest);

        assertThat(response.getStatus(), is(200));
        NewDonationResponse entity = (NewDonationResponse) response.getEntity();
        assertThat(entity.getDonationId(), is(donationId));
        assertThat(entity.getLink(), is(URI_DONATIONS + "/" + donationId));
    }

    @Test
    public void get_donation_by_id() throws Exception {
//        String donationId = "id123";
//        when(donationServiceFacade.getDonationById(donationId)).thenReturn(new ArrayList<DonationDTO>());
//        when(uriInfo.getRequestUriBuilder()).thenReturn(UriBuilder.fromPath(URI_DONATIONS));
//
//        Response response = donationResources.newDonation(uriInfo, deviceId, newDonationRequest);
//
//        assertThat(response.getStatus(), is(200));
//        NewDonationResponse entity = (NewDonationResponse) response.getEntity();
//        assertThat(entity.getDonationId(), is(donationId));
//        assertThat(entity.getLink(), is(URI_DONATIONS + "/" + donationId));
    }
}
