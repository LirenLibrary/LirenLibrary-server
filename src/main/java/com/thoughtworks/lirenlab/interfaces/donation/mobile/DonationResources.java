package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.google.common.collect.Collections2;
import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/")
public class DonationResources {

    private final DonationServiceFacade donationServiceFacade;

    @Autowired
    public DonationResources(final DonationServiceFacade donationServiceFacade) {
        this.donationServiceFacade = donationServiceFacade;
    }

    @GET
    @Path("donation/{id}")
    @Produces("application/vnd.liren-donation+json")
    @Versions(version = {"v1"})
    public Response donation(@QueryParam("id") String id) {
        return Response.status(200).entity(mockDonations().get(1)).build();
    }

    @GET
    @Path("donations")
    @Produces("application/vnd.liren-donations+json")
    @Versions(version = {"v1"})
    public Response donations() {
        return Response.status(200).entity(mockDonations()).build();
    }


    // Todo create db entity and data logic when come to the releated story, Here Just using mock data
    private List<DonationDTO> mockDonations() {
        BookDTO bookDTO1 = new BookDTO();
        bookDTO1.setIsbn("123");
        bookDTO1.setTitle("Foo");

        BookDTO bookDTO2 = new BookDTO();
        bookDTO2.setIsbn("456");
        bookDTO2.setTitle("Bar");

        ArrayList<BookDTO> bookDTOs = new ArrayList<BookDTO>();
        bookDTOs.add(bookDTO1);
        bookDTOs.add(bookDTO2);

        DonationDTO donation1 = new DonationDTO();
        donation1.setId("1");
        donation1.setCreatedDate("2012-12-01");
        donation1.setBookAmount(100);
        donation1.setBooks(bookDTOs);

        DonationDTO donation2 = new DonationDTO();
        donation2.setId("2");
        donation2.setCreatedDate("2012-12-02");
        donation2.setBookAmount(100);
        donation2.setBooks(bookDTOs);

        DonationDTO donation3 = new DonationDTO();
        donation3.setCreatedDate("2012-12-03");
        donation3.setId("3");
        donation3.setBookAmount(100);
        donation3.setBooks(bookDTOs);

        ArrayList<DonationDTO> donationDTOs = new ArrayList<DonationDTO>();
        donationDTOs.add(donation1);
        donationDTOs.add(donation2);
        donationDTOs.add(donation3);

        return donationDTOs;
    }

    @Path("donations")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newDonation(
            @Context UriInfo uriInfo,
            @HeaderParam("device_id") String deviceId,
            NewDonationRequest newDonationRequest) {
        List<BookDTO> books = newDonationRequest.getBooks();
        String donationId = donationServiceFacade.newDonation(deviceId, books);
        NewDonationResponse newDonationResponse = new NewDonationResponse();
        newDonationResponse.setDonationId(donationId);
        String link = uriInfo.getRequestUriBuilder().path(donationId).build().toString();
        newDonationResponse.setLink(link);
        return Response.ok().entity(newDonationResponse).build();
    }
}
