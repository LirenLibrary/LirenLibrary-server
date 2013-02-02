package com.thoughtworks.lirenlab.domain.model.donation;

public class DonationNotFoundException extends RuntimeException {

    private final DonationId donationId;

    public DonationNotFoundException(final DonationId donationId) {
        this.donationId = donationId;
    }

    public DonationId donationId() {
        return donationId;
    }
}
