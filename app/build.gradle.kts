plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "ru.netology.nmedia"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.netology.nmedia"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.viewBinding = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val lifecycle_version = "2.8.1"
    val recyclereview_version = "1.1.0"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation ("androidx.activity:activity-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.recyclerview:recyclerview:$recyclereview_version")
}