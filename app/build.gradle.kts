plugins {
    alias(libs.plugins.rickandmorty.android.application)
    alias(libs.plugins.rickandmorty.android.application.compose)
    alias(libs.plugins.rickandmorty.android.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.serialization)
}
android {

    defaultConfig {
        applicationId = "com.merttoptas.composebase"
        versionCode = 1
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.merttoptas.composebase"
}

dependencies {
   // implementation(projects.core.monitor)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.android.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.lottie.compose)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.hilt.android.testing)

    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.square.leakcanary)

    ksp(libs.moshi.kotlin.codegen)
}
