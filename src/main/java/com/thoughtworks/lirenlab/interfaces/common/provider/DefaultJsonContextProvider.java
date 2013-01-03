package com.thoughtworks.lirenlab.interfaces.common.provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static org.codehaus.jackson.map.SerializationConfig.Feature.INDENT_OUTPUT;

@Provider
@Component
public class DefaultJsonContextProvider implements ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultMapper();
    }

    private static ObjectMapper defaultMapper() {
        ObjectMapper result = new ObjectMapper();
        result.configure(INDENT_OUTPUT, true);
        return result;
    }
}
