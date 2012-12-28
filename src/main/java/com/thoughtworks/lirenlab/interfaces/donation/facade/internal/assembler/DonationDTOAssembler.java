package com.thoughtworks.lirenlab.interfaces.donation.facade.internal.assembler;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;

import java.util.List;

public class DonationDTOAssembler {

    public DonationDTO toDTO(Donation donation) {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setId(donation.id().strValue());
        donationDTO.setCreatedDate(donation.createdDate().toString());
        donationDTO.setBookAmount(donation.books().size());
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
}
