@file:Suppress("EnumNaming")

package template

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

enum class FlavorDimension {
    CONTENT_TYPE
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
enum class Flavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.CONTENT_TYPE, applicationIdSuffix = ".dev"), // For Local Development
    alpha(FlavorDimension.CONTENT_TYPE, applicationIdSuffix = ".alpha"), // For QA packages
    beta(
        FlavorDimension.CONTENT_TYPE,
        applicationIdSuffix = ".beta",
    ), // For Pre-Production packages (Regression Testing)
    prod(FlavorDimension.CONTENT_TYPE), // Production
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit = {},
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.CONTENT_TYPE.name
        productFlavors {
            Flavor.values().forEach { flavor ->
                create(flavor.name) {
                    val appName = buildString {
                        append("template")
                        flavor.applicationIdSuffix?.let {
                            append(" - ")
                            append(it.drop(1).uppercase())
                        }
                    }
                    resValue("string", "app_name", appName)
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
