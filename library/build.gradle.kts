plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

apply(from = "./publish.gradle.kts")

android {
    namespace = "com.telematicssdk.auth"

    group = "com.telematicssdk"
    version = "1.1.0"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("proguard-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

            buildConfigField("String", "USER_SERVICE_URL", "\"https://user.telematicssdk.com/\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "USER_SERVICE_URL", "\"https://user.telematicssdk.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.okhttp3)
    implementation(libs.retrofit.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
