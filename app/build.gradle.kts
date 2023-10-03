@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "br.com.rcp.bookstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.rcp.bookstore"
        minSdk = 21
        targetSdk = 33
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":bookservice"))
    implementation(project(":network-abstract"))
    implementation(project(":bookservice-abstract"))

    implementation(libs.core.ktx)
    implementation(libs.core.coroutine.ktx)

    implementation(libs.lifecycle.runtime.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.navigation)
    implementation(libs.compose.paging)
    implementation(libs.compose.coil)
    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.hilt.android.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.junit.android.extension)

    debugImplementation(libs.compose.ui.tooling)
}
