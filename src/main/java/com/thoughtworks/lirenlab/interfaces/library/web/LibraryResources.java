package com.thoughtworks.lirenlab.interfaces.library.web;

import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.interfaces.library.facade.LibraryServerFacade;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/libraries")
public class LibraryResources {

    private LibraryServerFacade libraryServerFacade;
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryResources.class);


    @Autowired
    public LibraryResources(LibraryServerFacade libraryServerFacade) {
        this.libraryServerFacade = libraryServerFacade;
    }

    @POST
    @Path("/new")
    @Consumes("application/vnd.liren-library+json")
    @Versions(version = {"v1"})
    public Response add(LibraryDTO dto) {
        LOGGER.info("the dto get from browser is: " + dto.getId() + ":" + dto.getAddress());
        LOGGER.info("postcode is: " + dto.getPostcode());
        libraryServerFacade.add(dto);
        LOGGER.info("library added: " + dto.getAddress());
//        libraryServerFacade.add(
//                dto.getName(),
//                dto.getContacter(),
//                dto.getAddress(),
//                dto.getPostcode(),
//                dto.getTelephone());
        return Response.ok().build();
    }

    @GET
    @Path("/all")
    @Produces("application/vnd.liren-libraries+json")
    @Versions(version = {"v1"})
    public Response findAllLibraries() {
        List<LibraryDTO> libraries1 = libraryServerFacade.findLibraries();
        List<LibraryDTO> libraries = Lists.newArrayList();
        LibraryDTO one = new LibraryDTO("chengdu", "jiangnan", "koly", "610046", "13881989076");
        one.setId("123456");
        libraries.add(one);


        LibraryDTO two = new LibraryDTO("sichuan", "thought", "aaaaa", "100006", "13881989076");
        one.setId("654321");
        libraries.add(two);

        return Response.ok().entity(libraries).build();
    }
}
