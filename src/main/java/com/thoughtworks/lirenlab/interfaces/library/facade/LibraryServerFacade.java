package com.thoughtworks.lirenlab.interfaces.library.facade;

import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;

import java.util.List;

public interface LibraryServerFacade {

    void add(LibraryDTO dto);

    List<LibraryDTO> findLibraries();
}
