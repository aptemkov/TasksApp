package io.github.aptemkov.tasksapp.domain.audio

interface AudioPlayer {
    fun startPlayer(fileName: String)
    fun playFile(fileName: String)
    fun pause()
    fun getDuration(): Int
    fun getCurrentPosition(): Int
    fun changePosition(position: Int)
    fun stop()
}