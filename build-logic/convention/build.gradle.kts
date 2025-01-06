plugins {
    `kotlin-dsl`
}

group = "io.pdaa.chilenastats.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidApplication"){
            id = "pdaa.android.application"
            implementationClass ="AndroidApplicationConventionPlugin"
        }
    }
}