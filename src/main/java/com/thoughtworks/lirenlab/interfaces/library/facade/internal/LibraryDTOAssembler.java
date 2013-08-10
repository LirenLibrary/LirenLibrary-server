package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.istack.Nullable;
import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

public class LibraryDTOAssembler {
    public static Library toLibrary(LibraryDTO dto) {
        Library library = new Library();
        try {
            BeanUtils.copyProperties(library, dto);
        } catch (Exception e) {
            throw new AssembleException(e);
        }
        return library;
    }

    public static List<LibraryDTO> toLibraryDTOs(List<Library> libraries) {
        List<LibraryDTO> libraryDTOs = Lists.transform(libraries, new Function<Library, LibraryDTO>() {
            @Override
            public LibraryDTO apply(@Nullable Library input) {
                try {
                    LibraryDTO dto = new LibraryDTO();
                    BeanUtils.copyProperties(input, dto);
                    return dto;
                } catch (Exception e) {
                    throw new AssembleException(e);
                }
            }
        });
        return libraryDTOs;
    }
}
