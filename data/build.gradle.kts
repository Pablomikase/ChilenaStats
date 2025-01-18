plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies{
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)

    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(project(":test:unit"))
}