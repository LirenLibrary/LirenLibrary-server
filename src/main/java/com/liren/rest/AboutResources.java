package com.liren.rest;

import com.liren.rest.dto.AboutDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: zhengli
 * Date: 12/5/12
 */
@Path("/about")
public class AboutResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AboutDTO about() {
        return new AboutDTO("metaphor", "Hello JoJo!");
    }
}
