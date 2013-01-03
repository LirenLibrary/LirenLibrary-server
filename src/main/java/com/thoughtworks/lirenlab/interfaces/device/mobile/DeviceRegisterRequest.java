package com.thoughtworks.lirenlab.interfaces.device.mobile;

import org.codehaus.jackson.annotate.JsonProperty;

public class DeviceRegisterRequest {

    @JsonProperty("device_token")
    private String deviceToken;


    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public String toString() {
        return "DeviceRegisterRequest{" +
                "deviceToken='" + deviceToken + '\'' +
                '}';
    }
}
