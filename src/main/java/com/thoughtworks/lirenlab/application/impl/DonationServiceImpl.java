package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;
    private DonationFactory donationFactory;

    @Autowired
    public DonationServiceImpl(final DonationRepository donationRepository,final DonationFactory donationFactory) {

        this.donationRepository = donationRepository;
        this.donationFactory = donationFactory;
    }

    @Override
    public DonationId requestDonation(DeviceId deviceId, List<Book> books) {
        Donation donation = donationFactory.createDonation(deviceId, books);
        DonationId donationId = donationRepository.save(donation);
        return donationId;
    }

}
