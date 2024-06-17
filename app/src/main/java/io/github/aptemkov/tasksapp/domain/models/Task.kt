package io.github.aptemkov.tasksapp.domain.models

data class Task(
    val id: String,
    val description: String,
    val priority: Priority,
    val deadline: Long = 0L,
    val isDone: Boolean,
    val createDate: Long = 0L,
    val editDate: Long = 0L,
)