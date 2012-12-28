package com.thoughtworks.lirenlab.interfaces.donation.facade;

import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;

import java.util.List;

public interface DonationServiceFacade {

    public List<DonationDTO> getNewDonations();
}
