import java.util.Properties

plugins {
    id("pdaa.android.application")
    id("pdaa.android.application.compose")
    id("pdaa.android.room")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinxSerialization)

}

android {
    namespace = "io.pdaa.chilenastats"
    compileSdk = 35



    defaultConfig {
        applicationId = "io.pdaa.chilenastats"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*vectorDrawables {
            useSupportLibrary = true
        }*/

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

        val footballApiKey = properties.getProperty("FOOTBAL_API_KEY")
        buildConfigField("String", "FOOTBAL_API_KEY", footballApiKey)

        val footballApiHost = properties.getProperty("FOOTBAL_API_HOST")
        buildConfigField("String", "FOOTBAL_API_HOST", footballApiHost)

        val addsApplicationId = properties.getProperty("ADDS_APPLICATION_ID")
        buildConfigField("String", "ADDS_APPLICATION_ID", addsApplicationId)

        val dashboardBannerId = properties.getProperty("DASHBOARD_BANNER_ID")
        buildConfigField("String", "DASHBOARD_BANNER_ID", dashboardBannerId)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention"))

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":usecases"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.logging.interceptor)
    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    //add mob
    implementation(libs.play.services.ads)
    implementation(libs.ui)
    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(project(":test:unit"))

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}