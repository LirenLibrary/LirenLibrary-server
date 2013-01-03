package com.thoughtworks.lirenlab.interfaces.donation.mobile;

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
        return Response.status(200).entity(mockDonation(id)).build();
    }

    @GET
    @Path("donations")
    @Produces("application/vnd.liren-donations+json")
    @Versions(version = {"v1"})
    public Response donations() {
        return Response.status(200).entity(donationServiceFacade.getNewDonations()).build();
    }


    // Todo create db entity and data logic when come to the releated story, Here Just using mock data
    private DonationDTO mockDonation(String id) {
        DonationDTO donation = new DonationDTO();
        donation.setId(id);
        donation.setBookAmount(100);
        return donation;
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
