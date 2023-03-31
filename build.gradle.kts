plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.dagger.hilt) apply false
}

/*
task clean(type: Delete) {
    delete rootProject.buildDir
}

subprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
        kotlinOptions {
            if (project.findProperty("myapp.enableComposeCompilerReports") == "true") {
                freeCompilerArgs += [
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                ]
                freeCompilerArgs += [
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                ]
            }
        }
    }
}
 */
