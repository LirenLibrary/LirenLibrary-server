package com.thoughtworks.lirenlab.integration;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.service.PushService;

public class FakeApnsPushService implements PushService {

    @Override
    public void notifyDonationApproved(Donation donation) {
        //do nothing
    }

    @Override
    public void notifyDonationRejected(Donation donation) {
        //do nothing
    }

    @Override
    public void notifyDonationReceived(Donation donation) {
        //do nothing
    }
}
