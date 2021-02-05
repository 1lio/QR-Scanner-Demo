plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "vi.sukhov.scanner"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    testOptions {

        animationsDisabled = true

        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {

    // Coroutines
    val coroutineVer = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVer")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVer")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutineVer")

    // Base
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.3.0-rc02")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:26.4.0"))
    // implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    // Navigation
    val navVer = "2.0.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVer")
    implementation("androidx.navigation:navigation-ui-ktx:$navVer")

    // CameraX
    val cameraxVer = "1.0.0-beta07"
    implementation("androidx.camera:camera-camera2:$cameraxVer")   // CameraX core library using camera2 implementation
    implementation("androidx.camera:camera-lifecycle:$cameraxVer") // CameraX Lifecycle Library
    implementation("androidx.camera:camera-view:1.0.0-alpha21")    // CameraX View class

    // DataStore
    implementation("androidx.datastore:datastore:1.0.0-alpha06")

    // Room
    val roomVer = "2.2.6"
    implementation("androidx.room:room-runtime:$roomVer")
    implementation("androidx.room:room-ktx:$roomVer")              // Kotlin Extensions and Coroutines support for Room
    kapt("androidx.room:room-compiler:$roomVer")

    // DI: Dagger Hilt
    val hiltVer = "2.31.2-alpha"
    implementation("com.google.dagger:hilt-android:$hiltVer")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVer")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    // WebSockets
    implementation("com.github.nkzawa:socket.io-client:0.6.0")

    // QR-Scanner
    implementation("com.dlazaro66.qrcodereaderview:qrcodereaderview:2.0.3")

    // Mics
    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:1.4.1")

    // Tests
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

kapt {
    correctErrorTypes = true
}
