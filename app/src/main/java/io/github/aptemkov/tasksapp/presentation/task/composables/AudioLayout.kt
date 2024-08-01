package io.github.aptemkov.tasksapp.presentation.task.composables

import android.content.Context
import android.provider.MediaStore.Audio
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.utils.requestAudioPermission
import io.github.aptemkov.tasksapp.app.utils.requestStoragePermissions
import io.github.aptemkov.tasksapp.app.utils.toMinutesAndSeconds
import io.github.aptemkov.tasksapp.presentation.task.AudioPlayerState
import io.github.aptemkov.tasksapp.presentation.task.TaskScreenUiState
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun AudioLayout(
    onChangeIsAudioInputOpen: () -> Unit,
    uiState: TaskScreenUiState,
    context: Context,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onChangeIsAudioAdded: (Boolean) -> Unit,
    onChangeAudioPlayerPosition: (Float) -> Unit,
    onStartPlaying: () -> Unit,
    onPausePlaying: () -> Unit,
    onStopPlaying: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = TasksTheme.colorScheme.backSecondary
        ),
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            AudioLayoutHeader(
                onChangeIsAudioInputOpen = onChangeIsAudioInputOpen,
                uiState = uiState
            )

            if (uiState.isAudioInputOpen) {
                context.requestAudioPermission()
                context.requestStoragePermissions()
                if (!uiState.isAudioAdded) {
                    AudioRecorderLayout(
                        uiState = uiState,
                        onStartRecording = onStartRecording,
                        onStopRecording = onStopRecording,
                        onChangeIsAudioAdded = onChangeIsAudioAdded
                    )
                }

                if (uiState.audioFile != null && uiState.isAudioAdded) {
                    AudioPlayerLayout(
                        uiState = uiState,
                        onChangeAudioPlayerPosition = onChangeAudioPlayerPosition,
                        onStartPlaying = onStartPlaying,
                        onPausePlaying = onPausePlaying,
                        onStopPlaying = onStopPlaying
                    )
                }
            }

        }
    }
}

@Composable
fun AudioPlayerLayout(
    uiState: TaskScreenUiState,
    onChangeAudioPlayerPosition: (Float) -> Unit,
    onStartPlaying: () -> Unit,
    onPausePlaying: () -> Unit,
    onStopPlaying: () -> Unit
) {
   Column(
       modifier = Modifier.fillMaxWidth(),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Slider(
           value = uiState.currentAudioPosition.toFloat(),
           onValueChange = { position ->
               onChangeAudioPlayerPosition(position)
           },
           valueRange = 0f..uiState.audioDuration.toFloat()
       )
       Row {
           Text(
               text = uiState.currentAudioPosition.toFloat().toLong()
                   .toMinutesAndSeconds(),
               color = TasksTheme.colorScheme.labelSecondary,
           )
           Spacer(modifier = Modifier.weight(1f))
           Text(
               text = uiState.audioDuration.toLong().toMinutesAndSeconds(),
               color = TasksTheme.colorScheme.labelSecondary,
           )
       }

       when (uiState.audioPlayerState) {
           AudioPlayerState.PLAYING -> {
               Button(
                   onClick = { onPausePlaying() }
               ) {
                   Text(text = stringResource(R.string.pause_audio))
               }
           }

           AudioPlayerState.PAUSED, AudioPlayerState.RECORDED -> {
               Button(
                   onClick = { onStartPlaying() }
               ) {
                   Text(text = stringResource(R.string.play_audio))
               }
           }

           else -> {}
       }
   }
}

@Composable
fun AudioRecorderLayout(
    uiState: TaskScreenUiState,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onChangeIsAudioAdded: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.audioPlayerState) {
            AudioPlayerState.NONE -> {
                Button(
                    onClick = {
                        onStartRecording()
                    }
                ) {
                    Text(text = stringResource(R.string.start_recording))
                }
            }

            AudioPlayerState.RECORDING -> {
                Button(
                    onClick = {
                        onStopRecording()
                        onChangeIsAudioAdded(true)
                    }
                ) {
                    Text(text = stringResource(R.string.stop_recording))
                }
            }

            else -> {}
        }
    }
}

@Composable
fun AudioLayoutHeader(
    onChangeIsAudioInputOpen: () -> Unit,
    uiState: TaskScreenUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChangeIsAudioInputOpen() }
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.add_audio),
            color = TasksTheme.colorScheme.labelPrimary,
            style = TasksTheme.typography.body,
        )
        Spacer(modifier = Modifier.weight(1f))

        val imageVector = if (uiState.isAudioInputOpen) Icons.Default.KeyboardArrowDown
        else Icons.Default.KeyboardArrowUp

        Icon(
            imageVector = imageVector,
            contentDescription = "Audio input",
            tint = TasksTheme.colorScheme.labelPrimary,
        )
    }
}
