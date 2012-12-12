package com.thoughtworks.lirenlab.rest;

import com.thoughtworks.lirenlab.domain.About;
import com.thoughtworks.lirenlab.rest.dto.AboutDTO;
import com.thoughtworks.lirenlab.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhengli
 * Date: 12/5/12
 */
@Path("/abouts")
@Component
public class AboutResources {

    private AboutService aboutService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AboutDTO> about() {

        prepareTestData();
        List<About> abouts = aboutService.getAll();
        return toDTOs(abouts);
    }

    private void prepareTestData() {
        aboutService.removeAll();
        aboutService.create(new About("metaphor", "Hello Metaphor!"));
        aboutService.create(new About("jojo", "Hello JoJo!"));
    }

    private List<AboutDTO> toDTOs(List<About> abouts) {
        List<AboutDTO> dtos = new ArrayList<AboutDTO>();
        for (About each : abouts) {
            dtos.add(new AboutDTO(each.getName(), each.getMessage()));
        }
        return dtos;
    }

    @Autowired
    public void setAboutService(AboutService aboutService) {
        this.aboutService = aboutService;
    }
}
