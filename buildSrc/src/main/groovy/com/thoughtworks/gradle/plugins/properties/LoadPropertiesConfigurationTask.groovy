package com.thoughtworks.gradle.plugins.properties

import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.tasks.*
import org.apache.commons.configuration.PropertiesConfiguration

class PropertiesConfigurationTask extends DefaultTask {

    def propertiesFiles = []

    @TaskAction
    void process() {
        propertiesFiles.each { prop ->
            project.logger.quiet "loading property file ${prop}"
            def configuration = new PropertiesConfiguration(prop)
            configuration.getKeys().each { key ->
                def value = configuration.getString(key)
//                project.logger.quiet "${key}=${value}"
                def camelKey = key.split('\\.')*.capitalize().join()
                def camelizedKey = camelKey.replaceFirst(camelKey[0], camelKey[0].toLowerCase())
                project.ext[camelizedKey] = value
            }
        }
    }

}
