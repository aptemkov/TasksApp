package io.github.aptemkov.tasksapp.domain.models

import androidx.compose.runtime.Immutable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Immutable
data class Task(
    val id: String,
    val description: String,
    val priority: Priority,
    val deadline: Long = 0L,
    val isDone: Boolean,
    val isRemoved: Boolean = false,
    val createDate: Long = 0L,
    val editDate: Long = 0L,
)

fun Long.toDateString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return format.format(date)
}