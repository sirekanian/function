import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.sirekanyan.version-checker")
}

group = "org.sirekanyan"
version = property("appVersionName") as String

kotlin {
    jvmToolchain(17)
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "org.sirekanyan.fun.Main"
            nativeDistributions {
                targetFormats(TargetFormat.Deb)
                packageName = "function"
                packageVersion = property("appVersionName") as String
            }
        }
    }
}
