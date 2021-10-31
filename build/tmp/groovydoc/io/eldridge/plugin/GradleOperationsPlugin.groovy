package io.eldridge.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleOperationsPluginExtension {
    String url
}

class GradleOperationsPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {

        GradleOperationsPluginExtension extension = project.extensions.create('url', GradleOperationsPluginExtension)

        project.task('checkIfExists') {
            doLast {
                return new URL(extension.url).openConnection().with {
                    requestMethod = 'HEAD'
                    connect()
                    responseCode == 200
                }
            }
        }
    }
}
