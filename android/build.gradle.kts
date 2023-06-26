plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("org.sirekanyan.version-checker")
}

group = "org.sirekanyan"
version = "0.1.0"

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.7.2")
}

android {
    namespace = "org.sirekanyan.fun"
    compileSdk = 33
    defaultConfig {
        applicationId = "org.sirekanyan.fun"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "0.1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
