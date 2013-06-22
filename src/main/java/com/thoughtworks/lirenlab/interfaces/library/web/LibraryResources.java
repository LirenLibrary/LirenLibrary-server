package com.thoughtworks.lirenlab.interfaces.library.web;

import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import com.thoughtworks.lirenlab.interfaces.library.facade.LibraryServerFacade;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
        libraryServerFacade.add(dto.getAddress());
        return Response.ok().build();
    }
}
