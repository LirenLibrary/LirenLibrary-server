package com.thoughtworks.lirenlab.application.impl;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.domain.model.library.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    @Transactional
    public void delete(String id) {
        libraryRepository.delete(id);
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Deprecated
    @Override
    @Transactional
    public String add(String name, String contact, String address, String postcode, String telephone) {
        Library library = new Library(name, contact, address, postcode, telephone);
        return add(library);
    }

    @Override
    @Transactional
    public String add(Library library) {
        LOGGER.info("library post_code is: " + library.getPostcode());
        return libraryRepository.save(library);
    }

    @Override
    @Transactional
    public void update(String id, Library library) {
        library.setId(id);
        library.setUpdatedDate();
        libraryRepository.update(library);
    }

    @Override
    public List<Library> findByNamePrefix(String prefix) {
        return libraryRepository.findByNamePrefix(prefix);
    }
}
