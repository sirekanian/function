plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("org.sirekanyan.version-checker")
}

group = "org.sirekanyan"
version = property("appVersionName") as String

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
        versionCode = (property("appVersionCode") as String).toInt()
        versionName = property("appVersionName") as String
        setProperty("archivesBaseName", "$applicationId-$versionName-$versionCode")
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }
}
