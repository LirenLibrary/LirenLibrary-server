package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationFactory;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.domain.model.donation.InvalidHistoricalDonationException;
import com.thoughtworks.lirenlab.domain.model.donation.PostSpecification;
import com.thoughtworks.lirenlab.domain.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.thoughtworks.lirenlab.domain.model.donation.DonationStatus.*;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;
    private DonationFactory donationFactory;
    private PushService pushService;

    @Autowired
    public DonationServiceImpl(final DonationRepository donationRepository,
                               final DonationFactory donationFactory,
                               final PushService pushService) {
        this.donationRepository = donationRepository;
        this.donationFactory = donationFactory;
        this.pushService = pushService;
    }

    @Override
    public DonationId newDonation(DeviceId deviceId, List<Book> books) {
        Donation donation = donationFactory.createDonation(deviceId, books);
        DonationId donationId = donationRepository.save(donation);
        return donationId;
    }

    @Override
    public void approveBook(DonationId donationId, String isbn) {
        Donation donation = donationRepository.find(donationId);
        donation.approve(isbn);
        donationRepository.save(donation);
    }

    @Override
    public void rejectBook(DonationId donationId, String isbn) {
        Donation donation = donationRepository.find(donationId);
        donation.reject(isbn);
        donationRepository.save(donation);
    }

    @Override
    public void updatePostSpecification(DonationId donationId, PostSpecification postSpecification) {
        Donation donation = donationRepository.find(donationId);
        donation.updatePostSpecification(postSpecification);
        donationRepository.save(donation);
    }

    @Override
    public void confirm(DonationId donationId) {
        Donation donation = donationRepository.find(donationId);
        donation.confirm();
        donationRepository.save(donation);

        onConfirm(donation);
    }

    @Override
    public Donation findHistorical(DonationId donationId) {
        Donation donation = donationRepository.find(donationId);
        if (donation.isHistorical()) return donation;
        throw new InvalidHistoricalDonationException(donationId);
    }

    @Override
    public void receive(DonationId donationId) {
        Donation donation = donationRepository.find(donationId);
        donation.receive();
        donationRepository.save(donation);
        onReceive(donation);
    }

    private void onConfirm(Donation donation) {
        if (donation.status().equals(APPROVED))
            pushService.notifyDonationApproved(donation);

        if (donation.status().equals(REJECTED))
            pushService.notifyDonationRejected(donation);
    }

    private void onReceive(Donation donation) {
        if (donation.status().equals(NOTIFIED))
            pushService.notifyDonationReceived(donation);
    }

}
