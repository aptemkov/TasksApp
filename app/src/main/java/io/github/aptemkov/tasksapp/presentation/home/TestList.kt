package io.github.aptemkov.tasksapp.presentation.home

import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task

// Позже исправлю под нормальное хранение данных
val testList = listOf(
    Task(id = "1", description = "Задача 1", priority = Priority.LOW, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "2", description = "Задача 2", priority = Priority.LOW, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),

    Task(id = "3", description = "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обрезается… и даже немного больше, чтобы точно заметить правильность", priority = Priority.DEFAULT, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "4", description = "Задача 4", priority = Priority.DEFAULT, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),

    Task(id = "5", description = "Задача 5", priority = Priority.HIGH, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "6", description = "Задача 6", priority = Priority.HIGH, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),
)