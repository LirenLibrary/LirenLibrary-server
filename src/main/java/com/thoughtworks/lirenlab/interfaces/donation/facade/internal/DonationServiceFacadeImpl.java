package com.thoughtworks.lirenlab.interfaces.donation.facade.internal;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.internal.assembler.DonationDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DonationServiceFacadeImpl implements DonationServiceFacade {

    private final DonationService donationService;
    private final DonationRepository donationRepository;
    private final DonationDTOAssembler assembler;

    @Autowired
    public DonationServiceFacadeImpl(final DonationService donationService, final DonationRepository donationRepository) {
        this.donationService = donationService;
        this.donationRepository = donationRepository;
        assembler = new DonationDTOAssembler();
    }

    @Override
    public List<DonationDTO> getNewDonations() {
        return assembler.toDTO(donationRepository.newDonations());
    }
}
