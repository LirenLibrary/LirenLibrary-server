package com.thoughtworks.lirenlab.domain.model.library;

import java.util.List;

public interface LibraryRepository {

    public void save(Library library);

    public List<Library> findAll();

    public void update(Library library);

    public void delete(Long id);
}
