buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.31.2-alpha")
        classpath("com.google.gms:google-services:4.3.5")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
