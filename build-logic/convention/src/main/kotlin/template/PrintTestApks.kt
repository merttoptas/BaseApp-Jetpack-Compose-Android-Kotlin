package template

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.BuiltArtifactsLoader
import com.android.build.api.variant.HasAndroidTest
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File

internal fun Project.configurePrintApksTask(extension: AndroidComponentsExtension<*, *, *>) {
    extension.onVariants { variant ->
        if (variant is HasAndroidTest) {
            val loader = variant.artifacts.getBuiltArtifactsLoader()
            val artifact = variant.androidTest?.artifacts?.get(SingleArtifact.APK)
            val javaSources = variant.androidTest?.sources?.java?.all
            val kotlinSources = variant.androidTest?.sources?.kotlin?.all

            val testSources = if (javaSources != null && kotlinSources != null) {
                javaSources.zip(kotlinSources) { javaDirs, kotlinDirs ->
                    javaDirs + kotlinDirs
                }
            } else javaSources ?: kotlinSources

            if (artifact != null && testSources != null) {
                tasks.register(
                    "${variant.name}PrintTestApk",
                    PrintApkLocationTask::class.java
                ) {
                    apkFolder.set(artifact)
                    builtArtifactsLoader.set(loader)
                    variantName.set(variant.name)
                    sources.set(testSources)
                }
            }
        }
    }
}

internal abstract class PrintApkLocationTask : DefaultTask() {
    @get:InputDirectory
    abstract val apkFolder: DirectoryProperty

    @get:InputFiles
    abstract val sources: ListProperty<Directory>

    @get:Internal
    abstract val builtArtifactsLoader: Property<BuiltArtifactsLoader>

    @get:Input
    abstract val variantName: Property<String>

    @TaskAction
    fun taskAction() {
        val hasFiles = sources.orNull?.any { directory ->
            directory.asFileTree.files.any {
                it.isFile && it.parentFile.path.contains("build${File.separator}generated").not()
            }
        } ?: throw UnsupportedOperationException("Cannot check androidTest sources")

        // Don't print APK location if there are no androidTest source files
        if (!hasFiles) {
            return
        }

        val builtArtifacts = builtArtifactsLoader.get().load(apkFolder.get())
            ?: throw IllegalStateException("Cannot load APKs")
        if (builtArtifacts.elements.size != 1)
            throw IllegalArgumentException("Expected one APK !")
        val apk = File(builtArtifacts.elements.single().outputFile).toPath()
        println(apk)
    }
}
