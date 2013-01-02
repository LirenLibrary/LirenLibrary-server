package com.thoughtworks.lirenlab.domain.model.donation;

import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

import java.util.List;

public interface DonationFactory {

    public Donation createDonation(DeviceId deviceId, List<Book> books);
}
