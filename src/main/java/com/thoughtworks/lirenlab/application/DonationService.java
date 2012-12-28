package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;

import java.util.List;

public interface DonationService {

    public DonationId requestDonation(DeviceId deviceId, List<Book> books);

}
