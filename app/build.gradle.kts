@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "matej.lamza.countries"
    compileSdk = 34

    defaultConfig {
        applicationId = "matej.lamza.countries"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core-data")))
    implementation(project(mapOf("path" to ":core-network")))
    implementation(project(mapOf("path" to ":core-model")))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.recyclerview)

    //region DI
    implementation(libs.koin)
    implementation(libs.koin.android)

    //Others
    implementation(libs.bindables)
    implementation(libs.whatIf)
    implementation(libs.bundler)
    implementation(libs.glide)

}
