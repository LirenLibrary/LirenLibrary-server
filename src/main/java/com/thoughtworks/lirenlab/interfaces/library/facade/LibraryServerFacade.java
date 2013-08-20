package com.thoughtworks.lirenlab.interfaces.library.facade;

import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;

import java.util.List;

public interface LibraryServerFacade {

    String add(LibraryDTO dto);

    List<LibraryDTO> findLibraries();

    void delete(String id);
}
