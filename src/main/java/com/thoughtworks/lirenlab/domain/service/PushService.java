package com.thoughtworks.lirenlab.domain.service;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;

public interface PushService {

    void notifyDonationApproved(Donation donation);

    void notifyDonationRejected(Donation donation);
}
