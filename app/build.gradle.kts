plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

android {
    namespace = "com.timife.shapegames"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.timife.shapegames"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")

    }
}

dependencies {
    implementation(Deps.coreKtx)
    implementation (Deps.lifecycle)

    implementation (Deps.activityCompose)
    implementation (Deps.composeUi)
    implementation (Deps.composeTooling)
    implementation (Deps.composeMaterial)
    testImplementation (Deps.junitTest)
    androidTestImplementation (Deps.junitExt)
//    androidTestImplementation (Deps.espresso)
    androidTestImplementation (Deps.composeUiTest)
    debugImplementation (Deps.composeUiToolingTest)
    debugImplementation (Deps.uiManifest)

    //di
    implementation(Deps.hilt)
    kapt(Deps.hiltAndroidCompiler)
    kapt (Deps.hiltCompiler)

    //Room
    implementation (Deps.room)
    implementation (Deps.roomKtx)
    kapt (Deps.roomCompiler)

    // Retrofit
    implementation (Deps.retrofit)
    implementation (Deps.moshi)
    implementation (Deps.okHttp)
    implementation (Deps.interceptor)
    implementation (Deps.coroutinesAdapter)

    // Compose dependencies
    implementation (Deps.viewModelCompose)
    implementation (Deps.materialIcons)
    implementation (Deps.flowLayout)
    implementation (Deps.pagingCompose)
    implementation (Deps.swipeRefresh)
    implementation (Deps.hiltCompose)

    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")




    // Coil
    implementation (Deps.coil)

    //Coroutines
    implementation (Deps.coroutineCore)
    implementation (Deps.coroutinesAndroid)
    implementation (Deps.coroutinesPlay)

    //Logging
    implementation (Deps.timber)

    // Chucker for analysing network traffic
    debugImplementation (Deps.chuckerLib)
    releaseImplementation (Deps.chuckerNoOp) //leak canary to detect memory leaks
    debugImplementation (Deps.canary)

    // Chucker for analysing network traffic
    debugImplementation (Deps.chuckerLib)
    releaseImplementation (Deps.chuckerNoOp)

    //Tests
    testImplementation (Deps.coroutinesTest)
    testImplementation (Deps.junitTest)
    testImplementation (Deps.coreTest)
    testImplementation (Deps.truth)
    testImplementation (Deps.turbine)
    testImplementation (Deps.mockk)
    testImplementation (Deps.runner)

    //Instrumentation Tests
    androidTestImplementation(Deps.runner)
//    androidTestImplementation (Deps.espresso)
    androidTestImplementation (Deps.junitExt)
    androidTestImplementation (Deps.truth)
    androidTestImplementation (Deps.coroutinesTest)
    androidTestImplementation (Deps.coreTest)
}