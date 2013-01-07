package com.thoughtworks.lirenlab.domain.model.donation;

import java.util.List;

public interface DonationRepository {

    DonationId save(Donation donation);

    List<Donation> newDonations();

    Donation find(DonationId id);
}
