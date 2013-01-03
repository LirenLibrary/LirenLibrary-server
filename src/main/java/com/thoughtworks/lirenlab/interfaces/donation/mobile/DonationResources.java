package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/donation")
public class DonationResources {

    @GET
    @Path("/{id}")
    @Produces("application/vnd.liren-donation+json")
    @Versions(version = {"v1"})
    public Response donation(@QueryParam("id") String id){
        return Response.status(200).entity(mockDonation(id)).build();
    }

    // Todo create db entity and data logic when come to the releated story, Here Just using mock data
    private DonationDTO mockDonation(String id){
        DonationDTO donation = new DonationDTO();
        donation.setId(id);
        donation.setBookAmount(100);
        return donation;
    }
}
