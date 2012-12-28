package com.thoughtworks.lirenlab.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.IOException;

public class YamlLoader {

    public static <T> T load(String location, Class<T> clazz) {
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
