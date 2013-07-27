package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.thoughtworks.lirenlab.application.LibraryService;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.interfaces.library.facade.LibraryServerFacade;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryServerFacadeImplTest {

    @Mock
    private LibraryService libraryService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_add_a_library(){
        LibraryServerFacade libraryServerFacade = new LibraryServerFacadeImpl(libraryService);
        String address = "Sichuan Chengdu tianfu";
        String name = "江南图书馆";
        String contacter = "koly";
        String postcode = "3000";
        String telephone = "13987645673";

        LibraryDTO dto = new LibraryDTO(address, name, contacter, postcode, telephone);
        libraryServerFacade.add(dto);

        verify(libraryService).add(any(Library.class));
    }
}
