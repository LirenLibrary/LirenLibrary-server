package com.thoughtworks.lirenlab.interfaces.library.facade;

import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;

public interface LibraryServerFacade {

    public LibraryDTO find(String id);

    public Long add(String name, String contacter, String address, String postcode, String telphone);
}
