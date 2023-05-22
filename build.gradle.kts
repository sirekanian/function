group = "org.sirekanyan"
version = "0.1.0"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    val kotlinVersion = "1.8.20"
    val androidPlugin = "8.0.1"
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("android") version kotlinVersion apply false
    id("com.android.application") version androidPlugin apply false
    id("com.android.library") version androidPlugin apply false
    id("org.jetbrains.compose") version "1.4.0" apply false
    id("org.sirekanyan.version-checker") version "1.0.6" apply false
    id("com.squareup.sqldelight") version "1.5.5" apply false
}
