rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "QR-Scanner"

include(":app")

dependencyResolutionManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}