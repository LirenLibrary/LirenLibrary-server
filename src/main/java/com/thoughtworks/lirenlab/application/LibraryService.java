package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.library.Library;

import java.util.List;

public interface LibraryService {
    public String add(String id, String address);

    public void update(String id, String address);
    public void delete(String id);
    public List<Library> findAll();

    public String add(String name, String contacter, String address, String postcode, String telphone);
}
