package com.thoughtworks.lirenlab.interfaces.donation.facade;

import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;

import java.util.List;

public interface DonationServiceFacade {

    public List<DonationDTO> getNewDonations();

    public String newDonation(String deviceId, List<BookDTO> books);

    DonationDTO getDonationById(String id);

    List<DonationDTO> getDonationsOfDevice(String deviceId);

    void approveBook(String donationId, String isbn);

    void rejectBook(String donationId, String isbn);
}
