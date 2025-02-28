plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.rickandmorty.android.hilt)
}

android {
    namespace = "com.merttoptas.composebase.core.monitor"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.kotlinx.serialization.json)
    //mplementation(libs.kotlinx.coroutines.android)
}
