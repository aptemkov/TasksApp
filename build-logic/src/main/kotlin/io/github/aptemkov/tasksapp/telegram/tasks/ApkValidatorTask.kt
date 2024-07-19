package io.github.aptemkov.tasksapp.telegram.tasks

import io.github.aptemkov.tasksapp.telegram.TelegramApi
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class ApkValidatorTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:Input
    abstract val taskEnabled: Property<Boolean>

    @get:Input
    abstract val maxSize: Property<Int>

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:Input
    abstract val chatId: Property<String>

    @get:Input
    abstract val token: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun validate() {

        val token = token.get()
        val chatId = chatId.get()
        val maxSize = maxSize.get()
        val taskEnabled = taskEnabled.get()

        if(taskEnabled) {
            val file = apkDir.get().asFile.listFiles()
                ?.filter { it.name.endsWith(".apk") }
                ?.first() ?: throw Exception(".apk file is not found")

            // File size in Bytes
            val apkSize = file.length()
            if (apkSize > maxSize) {
                runBlocking {
                    telegramApi.sendMessage(
                        message = "The size of this apk is $apkSize bytes",
                        token = token,
                        chatId = chatId
                    ).apply {
                        println("Validation status = $status")
                    }
                }
                throw Exception("The size of apk is more than $maxSize bytes")
            } else {
                println("Apk size is within the limits of $maxSize bytes")
                outputFile.asFile.get().writeText(apkSize.toString())
            }
        } else {
            println("Validation task is disabled")
        }

    }

}