package com.thoughtworks.lirenlab.interfaces.common.provider;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class VersionFilterFactory implements ResourceFilterFactory {

    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        Versions versions = am.getAnnotation(Versions.class);
        List<String> supportedVersions = new ArrayList<String>();
        if(versions != null){
            supportedVersions = Arrays.asList(versions.version());
            return Collections.<ResourceFilter>singletonList(new VersionFilter(supportedVersions));
        }
        return null;
    }

    private static class VersionFilter implements ResourceFilter, ContainerResponseFilter {

        private final List<String> supportedVersions;

        private VersionFilter(List<String> supportedVersions) {
            this.supportedVersions = supportedVersions;
        }

        @Override
        public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
            String version = request.getHeaderValue("Version");
            if (!supportedVersions.contains(version)) {
                response.setStatus(301);
                response.setEntity(null);
            }
            return response;
        }

        @Override
        public ContainerResponseFilter getResponseFilter() {
            return this;
        }

        @Override
        public ContainerRequestFilter getRequestFilter() {
            return null;
        }
    }
}
