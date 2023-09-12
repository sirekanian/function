plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("org.sirekanyan.version-checker")
    id("app.cash.sqldelight")
}

group = "org.sirekanyan"
version = property("appVersionName") as String

kotlin {
    jvmToolchain(17)
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    jvm("desktop")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.material3)
                api("com.google.zxing:core:3.5.2")
                api("app.cash.sqldelight:coroutines-extensions:2.0.0")
                api("io.ktor:ktor-client-cio:2.3.4")
                api("io.ktor:ktor-client-websockets:2.3.4")
                api("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
                api("org.slf4j:slf4j-simple:2.0.9")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":mlkit"))
                implementation("com.google.accompanist:accompanist-permissions:0.32.0")
                implementation("app.cash.sqldelight:android-driver:2.0.0")
                implementation("androidx.activity:activity-compose:1.7.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
            }
        }
    }
}

android {
    namespace = "org.sirekanyan.fun.common"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    flavorDimensionList += "type"
    productFlavors {
        create("function") { dimension = "type" }
        create("cookbook") { dimension = "type" }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        buildConfig = true
    }
}

sqldelight {
    databases {
        create("FunDatabase") {
            packageName.set("org.sirekanyan.fun.data")
        }
    }
}
