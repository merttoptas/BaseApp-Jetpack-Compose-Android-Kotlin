buildscript {
    val compose_version by extra("1.3.2")
    val hilt_version by extra("2.44.2")
    val accompanist_version by extra("0.24.3-alpha")
    val lifecycle_version by extra("2.5.1")
    val paging_version by extra("1.0.0-alpha17")
    val swiperefresh_version by extra("0.23.1")
    val room_version by extra("2.5.0")
    val kotlinx_version by extra("1.1.0")
    val datastore_version by extra("1.0.0")
    val coil_version by extra("2.2.2")
    val lottie_version by extra("4.2.2")
    val shimmer_version by extra("1.0.0")
    val navigation_version by extra("2.5.3")
    val splash_version by extra("1.0.0")
    val tracing_version by extra("1.2.0-alpha01")
    val retrofit_version by extra("2.9.0")
    val okhttp_version by extra("4.9.3")
    val chucker_version by extra("3.5.2")

}

plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
    id("com.google.gms.google-services") version "4.3.10" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
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
