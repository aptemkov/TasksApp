package io.github.aptemkov.tasksapp.app.utils

import android.Manifest
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import java.util.Locale
import java.util.concurrent.TimeUnit


fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.requestAudioPermission() {
    ActivityCompat.requestPermissions(
        this.getActivity() ?: return,
        arrayOf(Manifest.permission.RECORD_AUDIO),
        0
    )
}

fun Context.requestStoragePermissions() {
    ActivityCompat.requestPermissions(
        this.getActivity() ?: return,
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        ),
        0
    )
}


fun Long.toMinutesAndSeconds() = String.format(
    Locale.getDefault(),
    "%02d:%02d",
    TimeUnit.MILLISECONDS.toMinutes(this),
    TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
)
