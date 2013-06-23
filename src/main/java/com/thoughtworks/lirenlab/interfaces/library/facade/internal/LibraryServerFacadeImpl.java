package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.interfaces.library.facade.LibraryServerFacade;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryServerFacadeImpl implements LibraryServerFacade
{
    private LibraryService libraryService;

    @Autowired
    public LibraryServerFacadeImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Long add(Long id, String address) {
        return libraryService.add(id, address);
    }

    @Override
    public LibraryDTO find(String id) {
        return null;
    }
}
