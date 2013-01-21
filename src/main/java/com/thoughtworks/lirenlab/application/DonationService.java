package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.PostSpecification;

import java.util.List;

public interface DonationService {

    public DonationId newDonation(DeviceId deviceId, List<Book> books);

    void approveBook(DonationId donationId, String isbn);

    void rejectBook(DonationId donationId, String isbn);

    void updatePostSpecification(DonationId donationId, PostSpecification postSpecification);

    void confirm(DonationId donationId);
}
