package com.thoughtworks.lirenlab.utils;

import com.thoughtworks.lirenlab.domain.model.donation.Donation;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.IOException;

import static java.lang.String.*;

public class Fixtures {

    public static Donation loadDonation(final String name) {
        return doLoad(format("classpath:fixtures/donation/%s.yaml", name), Donation.class);
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
}
