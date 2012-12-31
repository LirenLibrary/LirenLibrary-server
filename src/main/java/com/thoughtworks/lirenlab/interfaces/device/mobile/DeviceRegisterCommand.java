package com.thoughtworks.lirenlab.interfaces.device.mobile;

import org.codehaus.jackson.annotate.JsonProperty;

public class DeviceRegisterCommand {

    @JsonProperty("device_token")
    private String deviceToken;


    @Override
    public String toString() {
        return "DeviceRegisterCommand{" +
                "deviceToken='" + deviceToken + '\'' +
                '}';
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
