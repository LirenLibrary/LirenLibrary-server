package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.library.Library;

import java.util.List;

public interface LibraryService {

    public void delete(String id);

    public List<Library> findAll();

    String add(Library library);

    @Deprecated
    public String add(String name, String contacter, String address, String postcode, String telphone);

    void update(String id, Library library);
}
