plugins {
    `kotlin-dsl`
}

gradlePlugin {

    plugins.register("telegram-reporter") {
        id = "telegram-reporter"
        implementationClass = "io.github.aptemkov.tasksapp.telegram.TelegramReporterPlugin"
    }

}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.client)
    implementation(libs.ktor.client.okhttp)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}