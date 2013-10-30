package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.library.Library;

import java.util.List;

public interface LibraryService {

    public void delete(String id);

    public List<Library> findAll();

    String add(Library library);

    @Deprecated
    public String add(String name, String contact, String address, String postcode, String telephone);

    void update(String id, Library library);

    List<Library> findByNamePrefix(String prefix);
}
