package io.github.aptemkov.tasksapp.domain.models

import androidx.compose.runtime.Stable
import java.text.SimpleDateFormat
import java.util.Date

@Stable
data class Task(
    val id: String,
    val description: String,
    val priority: Priority,
    val deadline: Long = 0L,
    val isDone: Boolean,
    val createDate: Long = 0L,
    val editDate: Long = 0L,
)

fun Long.toDateString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}