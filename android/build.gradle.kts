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

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "org.sirekanyan.fun"
    compileSdk = 34
    defaultConfig {
        applicationId = "org.sirekanyan"
        minSdk = 21
        targetSdk = 34
        versionCode = (property("appVersionCode") as String).toInt()
        versionName = property("appVersionName") as String
        setProperty("archivesBaseName", "$applicationId-$versionName-$versionCode")
    }
    flavorDimensionList += "type"
    productFlavors {
        create("function") {
            dimension = "type"
            applicationIdSuffix = ".function"
        }
        create("cookbook") {
            dimension = "type"
            applicationIdSuffix = ".cookbook"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
