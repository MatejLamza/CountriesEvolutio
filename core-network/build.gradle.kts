@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = "matej.lamza.core_network"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.sandwich)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)

    // json parsing
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // di
    implementation(libs.koin)
    implementation(libs.koin.android)
}
