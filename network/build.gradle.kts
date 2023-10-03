@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "br.com.rcp.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com/books/v1/volumes/\"")
            buildConfigField("String", "API_KEY_HEADER", "\"x-goog-api-key\"")
            buildConfigField("String", "API_KEY_VALUE", "\"AIzaSyBRrmFVG2bYyaGR-pUiFsCoNEKjpKCTHKo\"")
            buildConfigField("Long", "CACHE_SIZE", "102400L")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com/books/v1/volumes/\"")
            buildConfigField("String", "API_KEY_HEADER", "\"x-goog-api-key\"")
            buildConfigField("String", "API_KEY_VALUE", "\"AIzaSyBRrmFVG2bYyaGR-pUiFsCoNEKjpKCTHKo\"")
            buildConfigField("Long", "CACHE_SIZE", "102400L")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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
    implementation(project(":commons"))
    implementation(project(":network-abstract"))

    implementation(libs.core.ktx)
    implementation(libs.core.coroutine.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.logging.interceptor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.junit.android.extension)
}
