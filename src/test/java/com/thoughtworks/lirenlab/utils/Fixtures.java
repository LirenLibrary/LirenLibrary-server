package com.thoughtworks.lirenlab.utils;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import com.thoughtworks.lirenlab.interfaces.donation.facade.dto.DonationDTO;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.String.format;

public class Fixtures {

    public static Donation loadDonation(final String name) {
        return doLoad(format("classpath:fixtures/domain/donation/%s.yaml", name), Donation.class);
    }

    public static DonationDTO loadDonationDTO(final String name) {
        return doLoad(format("classpath:fixtures/dto/donation/%s.yaml", name), DonationDTO.class);
    }

    private static <T> T doLoad(final String location,final Class<T> clazz) {
        try {
            Yaml yaml = new Yaml();
            yaml.setBeanAccess(BeanAccess.FIELD);
            Resource resource = new DefaultResourceLoader().getResource(location);
            return yaml.loadAs(resource.getInputStream(), clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void dumpToConsole(Object object) {
        new Yaml().dump(object, new PrintWriter(System.out));
    }
}
