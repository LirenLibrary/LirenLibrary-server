package com.thoughtworks.lirenlab.interfaces.device.mobile;

import com.thoughtworks.lirenlab.interfaces.common.provider.Versions;
import com.thoughtworks.lirenlab.interfaces.device.facade.DeviceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/devices")
public class DeviceResources {

    private final DeviceServiceFacade deviceServiceFacade;

    @Autowired
    public DeviceResources(final DeviceServiceFacade deviceServiceFacade) {
        this.deviceServiceFacade = deviceServiceFacade;
    }

    @POST
    @Path("/register")
    @Consumes("application/vnd.liren-device-register-request+json")
    @Versions(version = {"v1"})
    public Response register(
            @HeaderParam("device_id") String deviceId,
            DeviceRegisterRequest request) {
        this.deviceServiceFacade.registerDevice(deviceId, request.getDeviceToken());
        return Response.ok().build();
    }
}
