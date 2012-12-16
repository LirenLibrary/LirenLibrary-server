package com.thoughtworks.gradle.plugins.properties

import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.tasks.*

/**
 * Properties Configuration Plugin provide the ability to
 * load properties from external properties file into project.ext container
 *
 * property key is converted to camel style
 * eg {@code db.connection.username} is converted to {@code dbConnectionUsername}
 *
 * then you can use {@code dbConnectionUsername} in anywhere of your current project
 */
class PropertiesConfigurationPlugin implements Plugin<Project> {

    private static final String TASK_NAME_LOAD_PROPERTIES = 'loadPropertiesConfiguration'

    void apply(Project project) {
        def extension = project.extensions.create(PropertiesConfigurationExtension.NAME, PropertiesConfigurationExtension)
        def task = project.tasks.add(TASK_NAME_LOAD_PROPERTIES, PropertiesConfigurationTask)

        task.propertiesFiles = extension.props

        project.afterEvaluate {
            project.tasks.findAll { TASK_NAME_LOAD_PROPERTIES != it.name}*.dependsOn TASK_NAME_LOAD_PROPERTIES
        }
    }
}