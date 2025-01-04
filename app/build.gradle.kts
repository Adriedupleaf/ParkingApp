plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

val navSdkVersion by extra("5.3.0")

android {
    namespace = "com.example.app3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app3"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:okhttp-dnsoverhttps:4.9.3")
    //noinspection GradleDependency
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.google.dagger:hilt-android:2.51")
    annotationProcessor("com.google.dagger:hilt-compiler:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")
    implementation("androidx.multidex:multidex:2.0.1")
    annotationProcessor("com.google.dagger:hilt-compiler:2.51")

    implementation("com.jakewharton.timber:timber:4.7.1")
    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    //noinspection GradleDependency
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //noinspection GradleDependency
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    //noinspection GradleDependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.0")
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-ui-ktx:2.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Import the BoM for the Firebase platform
    //noinspection GradleDependency
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    //noinspection GradleDependency
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    //noinspection GradleDependency
//    api("com.google.android.libraries.navigation:navigation:${navSdkVersion}")
    implementation("com.google.android.gms:play-services-maps:19.0.0")

}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.defaults.properties"
}
