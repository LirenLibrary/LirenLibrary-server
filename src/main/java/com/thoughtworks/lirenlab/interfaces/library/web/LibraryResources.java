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
        LOGGER.info("library added: " + dto);
        return Response.ok().build();
    }

    @GET
    @Path("/all")
    @Produces("application/vnd.liren-libraries+json")
    @Versions(version = {"v1"})
    public Response findAllLibraries() {
        List<LibraryDTO> libraries = libraryServerFacade.findLibraries();
        LOGGER.info("the first library from database is: " + libraries.get(0).toString());
        return Response.ok().entity(libraries).build();
    }
}
