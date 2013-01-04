package com.thoughtworks.lirenlab.infrastructure.factory;

import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.device.DeviceId;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.domain.model.donation.DonationFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thoughtworks.lirenlab.domain.model.donation.Donation.donation;

@Component
public class DonationFactoryImpl implements DonationFactory {

    @Override
    public Donation createDonation(DeviceId deviceId, List<Book> books) {
        return donation(deviceId, books);
    }
}
