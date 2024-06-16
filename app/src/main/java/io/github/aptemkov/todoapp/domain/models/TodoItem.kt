package io.github.aptemkov.todoapp.domain.models

data class TodoItem(
    val id: String,
    val description: String,
    val importance: Importance,
    val deadline: Long = 0L,
    val isDone: Boolean,
    val createDate: Long = 0L,
    val editDate: Long = 0L,
)