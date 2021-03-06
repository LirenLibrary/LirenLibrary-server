package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.interfaces.library.facade.LibraryServerFacade;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibraryServerFacadeImpl implements LibraryServerFacade
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServerFacadeImpl.class);
    private LibraryService libraryService;

    @Autowired
    public LibraryServerFacadeImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public String add(LibraryDTO dto) {
        return libraryService.add(LibraryDTOAssembler.toLibrary(dto));
    }

    @Override
    public List<LibraryDTO> findLibraries() {
        List<Library> libraries = libraryService.findAll();
        return LibraryDTOAssembler.toLibraryDTOs(libraries);
    }

    @Override
    public void delete(String id) {
        libraryService.delete(id);
    }
}
