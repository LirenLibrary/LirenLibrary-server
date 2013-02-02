package com.thoughtworks.lirenlab.domain.model.donation;

public class InvalidHistoricalDonationException extends RuntimeException {

    private final DonationId donationId;

    public InvalidHistoricalDonationException(DonationId donationId) {
        this.donationId = donationId;
    }

    public DonationId donationId() {
        return donationId;
    }
}
