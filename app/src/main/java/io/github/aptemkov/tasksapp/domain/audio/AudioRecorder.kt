package io.github.aptemkov.tasksapp.domain.audio

interface AudioRecorder {
    fun start(fileName: String)
    fun stop()
}