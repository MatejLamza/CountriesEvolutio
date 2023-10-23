@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "matej.lamza.core_data"
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
    //DI
    implementation(libs.koin)
    implementation(libs.koin.android)

    implementation(libs.sandwich)

    implementation(project(mapOf("path" to ":core-model")))
    implementation(project(mapOf("path" to ":core-network")))
}
