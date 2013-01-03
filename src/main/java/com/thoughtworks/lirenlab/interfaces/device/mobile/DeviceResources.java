package com.thoughtworks.lirenlab.interfaces.device.mobile;

import com.thoughtworks.lirenlab.interfaces.device.facade.DeviceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/device")
public class DeviceResources {

    private final DeviceServiceFacade deviceServiceFacade;

    @Autowired
    public DeviceResources(final DeviceServiceFacade deviceServiceFacade) {
        this.deviceServiceFacade = deviceServiceFacade;
    }

    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(
            @HeaderParam("device_id") String deviceId,
            DeviceRegisterRequest request) {
        this.deviceServiceFacade.registerDevice(deviceId, request.getDeviceToken());
        return Response.ok().build();
    }
}
