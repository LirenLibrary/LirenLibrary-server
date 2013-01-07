package com.thoughtworks.lirenlab.domain.model.donation;

import com.thoughtworks.lirenlab.domain.model.device.DeviceId;

import java.util.List;

public interface DonationRepository {

    DonationId save(Donation donation);

    List<Donation> newDonations();

    Donation find(DonationId id);

    List<Donation> find(DeviceId deviceId);
}
