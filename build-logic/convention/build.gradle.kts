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
        register("androidApplicationCompose"){
            id = "pdaa.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidRoom"){
            id = "pdaa.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmRetrofit"){
            id = "pdaa.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }
        register("jvmLibrary"){
            id = "pdaa.jvm.library"
            implementationClass = "io.pdaa.chilenastats.convention.JvmLibraryConventionPlugin"
        }
    }
}