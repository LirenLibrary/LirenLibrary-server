package com.thoughtworks.lirenlab.interfaces.library.facade.internal;

import com.thoughtworks.lirenlab.domain.model.library.Library;
import com.thoughtworks.lirenlab.interfaces.library.facade.dto.LibraryDTO;
import org.apache.commons.beanutils.BeanUtils;

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
}
