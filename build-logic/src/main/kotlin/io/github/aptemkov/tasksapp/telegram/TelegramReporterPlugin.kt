package io.github.aptemkov.tasksapp.telegram

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import io.github.aptemkov.tasksapp.telegram.tasks.ApkDetailsTask
import io.github.aptemkov.tasksapp.telegram.tasks.ApkValidatorTask
import io.github.aptemkov.tasksapp.telegram.tasks.TelegramReporterTask
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import java.io.File

class TelegramReporterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val androidComponents =
            project.extensions.findByType(AndroidComponentsExtension::class.java)
                ?: throw GradleException("Android not found")
        val versionCode = project.extensions.findByType(AppExtension::class.java)?.defaultConfig?.versionName ?: "1"
//        val versionCode = (project.extensions.getByName("android") as AppExtension).defaultConfig.versionName ?: "1"


        val extension = project.extensions.create("telegram", TelegramExtension::class)
        val telegramApi = TelegramApi(HttpClient(OkHttp))

        androidComponents.onVariants { variant ->
            val artifacts = variant.artifacts.get(SingleArtifact.APK)

            /**
             * Task for validating size of apk file
             *
             * /gradlew :app:validateApkForDebug
             *
             * /gradlew :app:validateApkForRelease
             */

            val validationTask = project.tasks.register(
                "validateApkFor${variant.name.capitalize()}",
                ApkValidatorTask::class.java,
                telegramApi
            ).apply {
                val isTaskEnabled = extension.validationEnabled.getOrElse(true)
                configure {
                    taskEnabled.set(isTaskEnabled)
                    apkDir.set(artifacts)
                    token.set(extension.token)
                    chatId.set(extension.chatId)
                    maxSize.set(extension.maxSize)
                    outputFile.set(File("apk-size-output.txt"))
                }
            }

            /**
             * Task for sending apk details to telegram
             *
             *  /gradlew :app:getApkDetailsForDebug
             *
             * /gradlew :app:getApkDetailsForRelease
             */

            val isDetailsTaskEnabled = extension.validationEnabled.getOrElse(true)
            val detailsTask = project.tasks.register(
                "getApkDetailsFor${variant.name.capitalize()}",
                ApkDetailsTask::class.java,
                telegramApi
            ).apply {
                configure {
                    taskEnabled.set(isDetailsTaskEnabled)
                    apkDir.set(artifacts)
                    token.set(extension.token)
                    chatId.set(extension.chatId)
                }
            }

            /**
             * Task for sending final report to telegram
             *
             * /gradlew :app:reportTelegramApkForDebug
             *
             * /gradlew :app:reportTelegramApkForRelease
             */

            val fileName = "todolist-${variant.name}-v.$versionCode.apk"
            val reporterTask = project.tasks.register(
                "reportTelegramApkFor${variant.name.capitalize()}",
                TelegramReporterTask::class.java,
                telegramApi
            ).apply {
                configure {
                    dependsOn(validationTask)
                    finalizedBy(detailsTask)
                    apkDir.set(artifacts)
                    token.set(extension.token)
                    chatId.set(extension.chatId)
                    inputFile.set(validationTask.get().outputFile)
                    resultFileName.set(fileName)
                }
            }
        }
    }
}

interface TelegramExtension {
    val token: Property<String>
    val chatId: Property<String>
    val maxSize: Property<Int>
    val validationEnabled: Property<Boolean>
    val detailsEnabled: Property<Boolean>
}
