package com.thoughtworks.lirenlab.application;

import com.thoughtworks.lirenlab.domain.model.library.Library;

import java.util.List;

public interface LibraryService {
    public Long add(Long id, String address);

    public void update(Long id, String address);
    public void delete(Long id);
    public List<Library> findAll();
}
