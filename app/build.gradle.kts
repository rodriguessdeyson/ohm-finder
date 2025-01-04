plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.rad.resistorcolorcode"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rad.resistorcolorcode"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }


    buildTypes {
        release {
            buildConfigField("String", "MAIN_ADMOB_ID", "\"ca-app-pub-4749632185565734/3542782502\"")
            buildConfigField("String", "MAIN_ADMOB_BANNER1_ID", "\"ca-app-pub-4749632185565734/9071570655\"")
            buildConfigField("String", "MAIN_ADMOB_BANNER2_ID", "\"ca-app-pub-4749632185565734/3471759617\"")
            buildConfigField("String", "NEW_RESISTOR_ADMOB_ID", "\"ca-app-pub-4749632185565734/4396699179\"")
            buildConfigField("String", "HELP_ADMOB_ID", "\"ca-app-pub-4749632185565734/9710923420\"")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "MAIN_ADMOB_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "MAIN_ADMOB_BANNER1_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "MAIN_ADMOB_BANNER2_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "NEW_RESISTOR_ADMOB_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "HELP_ADMOB_ID", "\"ca-app-pub-3940256099942544/1033173712\"")

            isMinifyEnabled = false
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.window)
    implementation(libs.androidsvg)
    implementation(libs.play.services.ads.v2330)
}