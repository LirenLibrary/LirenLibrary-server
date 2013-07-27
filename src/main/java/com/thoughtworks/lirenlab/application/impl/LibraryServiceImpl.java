package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public String add(String id, String address) {
        Library library = new Library(address);
        libraryRepository.save(library);
        return library.id();
    }

    @Override
    public void update(String id, String address) {
        Library library = new Library(address);
        library.setUpdatedDate();
        libraryRepository.update(library);
    }

    @Override
    public void delete(String id) {
        libraryRepository.delete(id);
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public String add(String name, String contacter, String address, String postcode, String telphone) {
        Library library = new Library(name, contacter, address, postcode, telphone);
        libraryRepository.save(library);
        return library.id();
    }
}