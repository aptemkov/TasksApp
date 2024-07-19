package io.github.aptemkov.tasksapp.telegram.tasks

import io.github.aptemkov.tasksapp.telegram.TelegramApi
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class TelegramReporterTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:InputFile
    abstract val inputFile: RegularFileProperty

    @get:Input
    abstract val token: Property<String>

    @get:Input
    abstract val chatId: Property<String>

    @get:Input
    abstract val resultFileName: Property<String>

    @TaskAction
    fun report() {
        val token = token.get()
        val chatId = chatId.get()
        val inputFileText = inputFile.get().asFile.readText()
        val fileName = resultFileName.get()

        apkDir.get().asFile.listFiles()
            ?.filter { it.name.endsWith(".apk") }
            ?.forEach {
                runBlocking {
                    telegramApi.sendMessage(
                        "Build finished \nApk size is $inputFileText bytes",
                        token,
                        chatId
                    ).apply {
                        println("Send build finished message status = $status")
                    }
                }
                runBlocking {
                    telegramApi.upload(
                        it,
                        token,
                        chatId,
                        fileName
                    ).apply {
                        println("Send file status = $status")
                    }
                }
            }
    }
}