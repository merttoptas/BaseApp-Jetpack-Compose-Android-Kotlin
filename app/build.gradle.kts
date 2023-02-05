plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.merttoptas.composebase"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.foundation:foundation-layout:1.3.1")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")

    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    // Optional - Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    //ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${rootProject.extra["lifecycle_version"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")
    implementation("androidx.navigation:navigation-compose:${rootProject.extra["navigation_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra["lifecycle_version"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra["lifecycle_version"]}")

    //Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hilt_version"]}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.okhttp3:okhttp:${rootProject.extra["okhttp_version"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["okhttp_version"]}")

    //DataStore
    implementation("androidx.datastore:datastore:${rootProject.extra["datastore_version"]}")
    implementation("androidx.datastore:datastore-preferences:${rootProject.extra["datastore_version"]}")

    //Accompanistaccompanist_version
    implementation("com.google.accompanist:accompanist-insets:${rootProject.extra["accompanist_version"]}")
    implementation("com.google.accompanist:accompanist-pager:${rootProject.extra["accompanist_version"]}")
    implementation("com.google.accompanist:accompanist-pager-indicators:${rootProject.extra["accompanist_version"]}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${rootProject.extra["accompanist_version"]}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${rootProject.extra["accompanist_version"]}")

    //Room
    implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

    //Coil
    implementation("io.coil-kt:coil-compose:${rootProject.extra["coil_version"]}")

    //Paging 3.0
    implementation("androidx.paging:paging-compose:${rootProject.extra["paging_version"]}")

    //Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${rootProject.extra["kotlinx_version"]}")

    //Chucker chucker_version
    debugImplementation("com.github.chuckerteam.chucker:library:${rootProject.extra["chucker_version"]}")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:${rootProject.extra["chucker_version"]}")

    //Splash
    implementation("androidx.core:core-splashscreen:${rootProject.extra["splash_version"]}")

    implementation("com.airbnb.android:lottie-compose:${rootProject.extra["lottie_version"]}")
    implementation("com.valentinilk.shimmer:compose-shimmer:${rootProject.extra["shimmer_version"]}")

    implementation("com.google.accompanist:accompanist-navigation-animation:${rootProject.extra["accompanist_version"]}")

    implementation("androidx.tracing:tracing:${rootProject.extra["tracing_version"]}")
    implementation("androidx.tracing:tracing-ktx:${rootProject.extra["tracing_version"]}")


    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:$${rootProject.extra["compose_version"]}")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}