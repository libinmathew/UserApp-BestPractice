plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.userapp"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.userapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            resValue("string", "app_name", "UserApp")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "base_url",
                "\"https://reqres.in/api/\""
            )

            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            resValue("string", "app_name", "UserApp-Debug")
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "base_url",
                "\"https://reqres.in/api/\""
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //room
    implementation(libs.room.ktx)
    ksp(libs.androidx.room.compiler)

    //navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    //image-coil
    implementation(libs.coil.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}