import org.gradle.api.Plugin
import org.gradle.api.Project

class FirebasePerfConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.findPlugin("com.google.firebase.firebase-perf").apply {
                version = "1.4.1"
            }
        }
    }

}
