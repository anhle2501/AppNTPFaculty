
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    kotlin("kapt")
//    id("com.google.dagger.hilt.android")

}

android {
    namespace = "vn.bvntp.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.bvntp.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
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
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // crypto
    implementation ("androidx.security:security-crypto:1.0.0")
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    var nav_version = "2.7.7"

    // Java language implementation
    implementation ("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    // Kotlin
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    // Feature module Support
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    // Testing Navigation
    androidTestImplementation ("androidx.navigation:navigation-testing:$nav_version")
    // Jetpack Compose Integration
    implementation ("androidx.navigation:navigation-compose:$nav_version")


//    implementation("com.google.dagger:hilt-android:2.44")
//    kapt("com.google.dagger:hilt-compiler:2.44")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Nếu bạn muốn sử dụng Gson Converter

    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta01")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    //bar code
    implementation ("com.google.zxing:core:3.5.3")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    // asyn image
//    implementation("io.coil-kt:coil-compose:2.6.0")
    // pdf viewer
//    implementation("io.github.grizzi91:bouquet:1.1.2")
    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")




//    implementation("io.github.afreakyelf:Pdf-Viewer:2.1.1")

//    implementation("com.itextpdf:itextpdf:5.5.3")
//    implementation("com.artifex.mupdf:fitz:1.22.0")
//    implementation("org.apache.pdfbox:pdfbox:2.0.27")

//    implementation("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

//    implementation("com.syncfusion:flutter_pdfviewer:2.11.338")

//    implementation("com.tom-roush:pdfbox-android:2.0.0.27")
//    implementation("com.gemalto.jp2:jp2-android:1.0.3")
//    implementation("com.github.jai-imageio:jai-imageio-jpeg2000:1.4.0")
//    implementation("com.github.jai-imageio:jai-imageio-core:1.4.0")
//    implementation("com.twelvemonkeys.imageio:imageio-jpeg:3.10.1")



}


//
//kapt {
//    correctErrorTypes = true
//}
//
//hilt {
//    enableAggregatingTask = true
//}