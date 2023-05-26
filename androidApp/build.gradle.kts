plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, CFlow for native and binding extensions
    commonMainApi("dev.icerock.moko:mvvm-livedata:0.16.1") // api mvvm-core, LiveData and extensions
    commonMainApi("dev.icerock.moko:mvvm-state:0.16.1") // api mvvm-livedata, ResourceState class and extensions
    commonMainApi("dev.icerock.moko:mvvm-livedata-resources:0.16.1") // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
    commonMainApi("dev.icerock.moko:mvvm-flow-resources:0.16.1") // api mvvm-core, moko-resources, extensions for Flow with moko-resources

    // compose multiplatform
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatfrom
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1") // api mvvm-flow, binding extensions for Compose Multiplatfrom
    commonMainApi("dev.icerock.moko:mvvm-livedata-compose:0.16.1") // api mvvm-livedata, binding extensions for Compose Multiplatfrom
    commonTestImplementation("dev.icerock.moko:mvvm-test:0.16.1") // test utilities
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.myapplication.MyApplication"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
