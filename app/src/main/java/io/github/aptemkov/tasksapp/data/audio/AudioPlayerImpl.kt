package io.github.aptemkov.tasksapp.data.audio

import android.content.Context
import android.media.MediaPlayer
import android.os.Environment
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.aptemkov.tasksapp.domain.audio.AudioPlayer
import java.io.File
import javax.inject.Inject

class AudioPlayerImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AudioPlayer {

    private var player: MediaPlayer? = null

    override fun startPlayer(fileName: String) {

        val newAudioFile = getFileDir(fileName)

        if(player == null) {
            MediaPlayer.create(context, newAudioFile.toUri()).apply {
                player = this
            }
        }
    }

    override fun playFile(fileName: String) {
        if(player == null) {
            startPlayer(fileName)
        } else {
            player?.start()
        }

    }

    override fun getDuration(): Int {
        return player?.duration ?: 0
    }

    override fun getCurrentPosition(): Int {
        return player?.currentPosition ?: 0
    }

    override fun changePosition(position: Int) {
        player?.seekTo(position)
    }

    override fun pause() {
        player?.pause()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    private fun getFileDir(fileName: String): File {

        val recordingsDirectory = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        if (recordingsDirectory?.exists() == false) {
            recordingsDirectory.mkdirs()
        }
        val newAudioFile = File(recordingsDirectory, "recording_$fileName.mp4")
        return newAudioFile

    }

}