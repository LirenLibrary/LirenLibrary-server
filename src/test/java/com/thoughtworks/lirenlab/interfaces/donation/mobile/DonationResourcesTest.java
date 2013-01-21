package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.utils.Fixtures;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    public void get_donations_of_specified_device() throws Exception {

        DonationDTO donationDTO = Fixtures.loadDonationDTO("basic_1");
        String deviceId = "iphone5";

        when(donationServiceFacade.getDonationsOfDevice(deviceId)).thenReturn(newArrayList(donationDTO));

        Response response = donationResources.donationsOfDevice(deviceId);

        assertThat(response.getStatus(), is(200));
        assertThat((List<DonationDTO>)response.getEntity(), hasItem(donationDTO));
    }

    @Test
    public void should_approve_book_of_donation_by_isbn() throws Exception {
        String isbn = "isbn";
        String donationId = "12345";
        Response response = donationResources.approveBook(donationId, isbn);

        assertThat(response.getStatus(), is(200));
        verify(donationServiceFacade).approveBook(donationId, isbn);
    }

    @Test
    public void should_reject_book_of_donation_by_isbn() throws Exception {
        String isbn = "isbn";
        String donationId = "12345";
        Response response = donationResources.rejectBook(donationId, isbn);

        assertThat(response.getStatus(), is(200));
        verify(donationServiceFacade).rejectBook(donationId, isbn);
    }

    @Test
    public void should_update_post_specification_of_donation() throws Exception {
        String postSpecification = "postSpecification";
        String donationId = "12345";
        UpdatePostSpecificationRequest request = new UpdatePostSpecificationRequest();
        request.setSpecification(postSpecification);
        Response response = donationResources.updatePostSpecification(donationId, request);

        assertThat(response.getStatus(), is(200));
        verify(donationServiceFacade).updatePostSpecification(donationId, postSpecification);
    }

    @Test
    public void should_confirm_donation() throws Exception {
        String donationId = "12345";
        Response response = donationResources.confirm(donationId);

        assertThat(response.getStatus(), is(200));
        verify(donationServiceFacade).confirm(donationId);
    }
}
