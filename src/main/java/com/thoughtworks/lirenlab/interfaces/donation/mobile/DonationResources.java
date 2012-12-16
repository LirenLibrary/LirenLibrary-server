package com.thoughtworks.lirenlab.interfaces.donation.mobile;

import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/donation")
public class DonationResources {

    @GET
    @Path("/{id}")
    @Produces("application/vnd.liren-donation+json;charset=UTF-8")
    @Versions(version = {"v1"})
    public Response donation(@QueryParam("id") String id){
        return Response.status(200).entity(mockDonation()).build();
    }

    // Todo create db entity and data logic when come to the releated story, Here Just using mock data
    private Donation mockDonation(){
        Donation donation = new Donation();
        donation.setDonationId("837462");
        donation.setDonationStatus("ready");
        donation.setPostAddress("TianFu Soft Park");
        donation.setPostReceiver("Li San");
        donation.setPostCode("610000");
        donation.setPostReceiverMobile("13900000000");

        Book book1 = new Book();
        book1.setISBN("100000001");
        book1.setStatus("approved");

        Book book2 = new Book();
        book2.setISBN("100000002");
        book2.setStatus("rejected");

        Book book3 = new Book();
        book3.setISBN("100000003");
        book3.setStatus("received");

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        donation.setBookList(bookList);

        return donation;
    }
}
