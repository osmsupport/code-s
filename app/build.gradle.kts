plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.cricketapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cricketapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.8.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Client
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    //OkHttp Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    //Gson
    implementation("com.google.code.gson:gson:2.10.1")
    //sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    //ssp
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    //picasso
    implementation("com.squareup.picasso:picasso:2.8")
    //Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    //circular imageview
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

}
kapt {
    correctErrorTypes = true
}