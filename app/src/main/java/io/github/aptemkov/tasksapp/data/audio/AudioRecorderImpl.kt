package io.github.aptemkov.tasksapp.data.audio

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.aptemkov.tasksapp.data.remote.audio.uploadFile
import io.github.aptemkov.tasksapp.domain.audio.AudioRecorder
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class AudioRecorderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AudioRecorder {

    private var recorder: MediaRecorder? = null
    private var path: File? = null

    private fun createRecorder(): MediaRecorder {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    override fun start(fileName: String) {

        val newAudioFile = getFileDir(fileName)
        path = newAudioFile

        Log.i("AudioRecorder", "directory: ${newAudioFile.absolutePath} ")

        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioChannels(2)
            setAudioEncodingBitRate(44_100*16)
            setAudioSamplingRate(44_100)
            setOutputFile(newAudioFile.absolutePath)

            prepare()
            start()

            recorder = this
        }
    }

    override fun stop() {

        if(path != null) {
            Log.i("AudioRecorder", "stop: $path ")
            uploadFile(
                path?.absolutePath.toString(),
                "DL3MTQF.87MYWW9-WND4E1C-M1NER16-2K2GKP3"
            )
        }

        Log.i("AudioRecorder", "stop null: $path ")

        recorder?.stop()
        recorder?.reset()
        recorder = null
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
