plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("org.sirekanyan.version-checker")
    id("com.squareup.sqldelight")
}

group = "org.sirekanyan"
version = "0.1.0"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.material3)
                api("com.google.zxing:core:3.5.1")
                api("com.squareup.sqldelight:coroutines-extensions:1.5.5")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:sqlite-driver:1.5.5")
            }
        }
    }
}

android {
    namespace = "org.sirekanyan.fun"
    compileSdk = 33
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    database("FunDatabase") {
        packageName = "org.sirekanyan.fun.data"
    }
}
