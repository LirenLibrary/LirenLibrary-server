package com.thoughtworks.lirenlab.interfaces.library.facade;

import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;

public interface LibraryServerFacade {

    public Long add(Long id, String address);

    public LibraryDTO find(String id);
}
