package com.thoughtworks.lirenlab.domain.model.donation;

import java.util.List;

public interface DonationFactory {

    public Donation createDonation(DeviceId deviceId, List<Book> books);
}
