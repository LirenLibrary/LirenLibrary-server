package com.thoughtworks.lirenlab.infrastructure.apns;

import com.thoughtworks.lirenlab.domain.model.device.Device;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.device.DeviceRepository;
import com.thoughtworks.lirenlab.domain.service.PushService;
import javapns.Push;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class ApnsPushService implements PushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApnsPushService.class);

    @Value("${apns.cert.password}")
    private String password;

    @Value("${apns.cert.file}")
    private Resource cerFile;

    @Value("${apns.production}")
    private Boolean production = Boolean.FALSE;

    private DeviceRepository deviceRepository;

    @Autowired
    public ApnsPushService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void push(DeviceId deviceId, final String message) {
        Device device = deviceRepository.find(deviceId);
        LOGGER.info("pushing {} to device {}", message, device.id().strValue());
        doPush(message, device);
    }

    protected void doPush(String message, Device device) {
        try {
            Push.alert(
                    message,
                    getCerFile().getFile().getAbsolutePath(),
                    getPassword(),
                    getProduction(),
                    newArrayList(device.token().strValue()));
        } catch (Exception e) {
            LOGGER.error("failed to send notification to device {}, {} ", device, e.getMessage());
        }
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setCerFile(Resource cerFile) {
        this.cerFile = cerFile;
    }

    public void setProduction(Boolean production) {
        this.production = production;
    }

    public String getPassword() {
        return password;
    }

    public Resource getCerFile() {
        return cerFile;
    }

    public Boolean getProduction() {
        return production;
    }
}
