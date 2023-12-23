plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.ch8"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ch8"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            //isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug{
            isDebuggable = true
            versionNameSuffix = "-debug"
        }
    }

    flavorDimensionList += listOf("version")

    productFlavors{
//        create("demo"){
//            dimension = "version"
//            applicationIdSuffix = ".demo"
//            resValue("string", "app_name", "Demo")
//        }
        create("production"){
            dimension = "version"
            applicationIdSuffix = ".prod"
            resValue("string", "app_name", "Production")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")

    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
//    testImplementation ("org.robolectric:robolectric:4.6.1")
    testImplementation ("io.mockk:mockk:1.12.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.4.0")

    // networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.coil-kt:coil:2.5.0")
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // local
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // work manager
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // image picker
    implementation("com.github.esafirm:android-image-picker:3.0.0")

    // di koin
    implementation("io.insert-koin:koin-bom:3.5.1")
    implementation("io.insert-koin:koin-android")
    implementation("io.insert-koin:koin-androidx-workmanager")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
}
