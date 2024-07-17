package io.github.aptemkov.tasksapp.telegram.tasks

import io.github.aptemkov.tasksapp.telegram.TelegramApi
import io.ktor.http.isSuccess
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.util.zip.ZipFile
import javax.inject.Inject

abstract class ApkDetailsTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:Input
    abstract val taskEnabled: Property<Boolean>

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:Input
    abstract val token: Property<String>

    @get:Input
    abstract val chatId: Property<String>

    @TaskAction
    fun report() {
        val taskEnabled = taskEnabled.get()
        val token = token.get()
        val chatId = chatId.get()

        if (taskEnabled) {
            val file = apkDir.get().asFile.listFiles()
                ?.filter { it.name.endsWith(".apk") }
                ?.last()


            val details = ZipFile(file).entries()
                .asSequence()
                .sortedByDescending { it.size }
                .filter { !it.isDirectory && it.size > 1024.0 * 10}
                .map { entry ->
                    val sizeMb = entry.size / 1024.0 / 1024.0
                    ("`\\- ${entry.name} ${"%.2f".format(sizeMb)} MB`")
                }
                .joinToString("\n")

            runBlocking {
                telegramApi.sendMessage(
                    details,
                    token,
                    chatId
                ).apply {
                    println(details)
                    println("Details status = $status")
                    if (!status.isSuccess()) {
                        println(status.description)
                    }
                }
            }
        } else {
            println("Details task is disabled")
        }


    }
}