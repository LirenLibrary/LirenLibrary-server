package com.thoughtworks.lirenlab.interfaces.donation.facade.internal;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.thoughtworks.lirenlab.application.DonationService;
import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.DonationId;
import com.thoughtworks.lirenlab.domain.model.donation.DonationRepository;
import com.thoughtworks.lirenlab.interfaces.donation.facade.DonationServiceFacade;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.internal.assembler.DonationDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.thoughtworks.lirenlab.domain.model.device.DeviceId.deviceId;

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

    @Override
    public String newDonation(String deviceId, final List<BookDTO> bookDTOs) {
        Iterable<Book> books = Iterables.transform(bookDTOs, new Function<BookDTO, Book>() {
            @Override
            public Book apply(BookDTO input) {
               return Book.newBook(input.getIsbn(), input.getTitle());
            }
        });

        DonationId donationId = donationService.newDonation(deviceId(deviceId), newArrayList(books));
        return donationId.strValue();
    }
}
