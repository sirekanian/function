group = "org.sirekanyan"
version = property("appVersionName") as String

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    val kotlinVersion = "1.9.10"
    val androidPlugin = "8.1.1"
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("android") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
    id("com.android.application") version androidPlugin apply false
    id("com.android.library") version androidPlugin apply false
    id("org.jetbrains.compose") version "1.5.1" apply false
    id("org.sirekanyan.version-checker") version "1.0.8" apply false
    id("app.cash.sqldelight") version "2.0.0" apply false
}
