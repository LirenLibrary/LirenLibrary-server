package com.thoughtworks.gradle.plugins.properties

class PropertiesConfigurationExtension {

    def static final String NAME = 'propertiesConfiguration'

    def props = []

    def load(String filePath) {
        props << filePath
    }
}

