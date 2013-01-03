package com.thoughtworks.lirenlab.interfaces.donation.facade.internal.assembler;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.donation.Book;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.BookDTO;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;

import java.util.List;

public class DonationDTOAssembler {

    public DonationDTO toDTO(Donation donation) {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setId(donation.id().strValue());
        donationDTO.setCreatedDate(donation.createdDate().toString());
        donationDTO.setBookAmount(donation.books().size());
        donationDTO.setBooks(toBookDTO(donation.books()));
        return donationDTO;
    }

    public List<DonationDTO> toDTO(List<Donation> donations) {
        return Lists.newArrayList(
                Iterables.transform(donations,
                        new Function<Donation, DonationDTO>() {
                            @Override
                            public DonationDTO apply(Donation donation) {
                                return toDTO(donation);
                            }
                        }));
    }

    public List<BookDTO> toBookDTO(List<Book> books) {
        return Lists.newArrayList(
                Iterables.transform(books, new Function<Book, BookDTO>() {
                    @Override
                    public BookDTO apply(Book input) {
                        BookDTO dto = new BookDTO();
                        dto.setIsbn(input.isbn());
                        dto.setStatus(input.status().toString());
                        dto.setTitle(input.title());
                        return dto;
                    }
                })
        );

    }
}
