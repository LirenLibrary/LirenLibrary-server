package com.thoughtworks.lirenlab.domain.model.library;

import java.util.List;

public interface LibraryRepository {

    public String save(Library library);

    public List<Library> findAll();

    public void update(Library library);

    public void delete(String id);

    public List<Library> findByNamePrefix(String prefix);
}
