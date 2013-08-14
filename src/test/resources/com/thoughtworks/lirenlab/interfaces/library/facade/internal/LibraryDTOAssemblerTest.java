package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.google.common.collect.Lists;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryDTOAssemblerTest {
    @Test
    public void should_transform_libraryDTO_to_library() throws Exception {
        LibraryDTO dto = new LibraryDTO("江南图书馆", "江南", "志恒", "610000", "13897656754");

        Library library = LibraryDTOAssembler.toLibrary(dto);

        assertThat(library.getName(), is("江南"));
        assertThat(library.getAddress(), is("江南图书馆"));
        assertThat(library.getContacter(), is("志恒"));
        assertThat(library.getPostcode(), is("610000"));
        assertThat(library.getTelephone(), is("13897656754"));
    }

    @Test
    public void testToLibraryDTOs() throws Exception {
        Library libraryA = new Library("jiangnan", "koly", "sichuan chengdu", "610065", "13887654321");
        libraryA.setId("402881e54077c629014077c69ccb0000");
        Library libraryB = new Library("jiangnan", "koly", "sichuan chengdu", "610063", "19087654321");
        libraryB.setId("402881e54077c629014077c69ccb0001");
        List<Library> libraries = Lists.newArrayList(
                libraryA,
                libraryB
        );

        List<LibraryDTO> libraryDTOs = LibraryDTOAssembler.toLibraryDTOs(libraries);

        assertThat(libraryDTOs.size(), is(2));
        LibraryDTO firstLibrary = libraryDTOs.get(0);
        assertThat(firstLibrary.getId(), is("402881e54077c629014077c69ccb0000"));
        assertThat(firstLibrary.getName(), is("jiangnan"));
        assertThat(firstLibrary.getContacter(), is("koly"));
        assertThat(firstLibrary.getAddress(), is("sichuan chengdu"));
        assertThat(firstLibrary.getTelephone(), is("13887654321"));
        assertThat(firstLibrary.getPostcode(), is("610065"));
    }
}
