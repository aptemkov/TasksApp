package io.github.aptemkov.tasksapp.presentation.home

import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task

// Позже исправлю под нормальное хранение данных
val testList = mutableListOf(
    Task(id = "0", description = "Поставить автору кода хорошую оценку за выполнение этой домашки :)", priority = Priority.HIGH, deadline = 1719259200000, isDone = false, createDate = 1719001916L, editDate = 1719001916L),
    Task(id = "1", description = "Улыбнуться", priority = Priority.LOW, deadline = 19291L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "2", description = "Утром пресс качат, бегит, анжуманя, турник", priority = Priority.LOW, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
    Task(id = "3", description = "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обрезается… и даже немного больше, чтобы точно заметить правильность", priority = Priority.DEFAULT, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "4", description = "Сходить в магазин за покупками", priority = Priority.DEFAULT, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),

    Task(id = "5", description = "Сделать задачу ниже", priority = Priority.HIGH, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "6", description = "Сделать задачу выше", priority = Priority.HIGH, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
    Task(id = "7", description = "Просто текст", priority = Priority.DEFAULT, deadline = 11127821L, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "8", description = "Сделать задачу выше", priority = Priority.HIGH, deadline = 19957821L, isDone = true, createDate = 0L, editDate = 0L),
    Task(id = "9", description = "Слетать на Марс", priority = Priority.LOW, deadline = 1000, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "10", description = "Задача номер 10", priority = Priority.LOW, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "11", description = "Задача номер 11", priority = Priority.LOW, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "12", description = "Задача номер 12", priority = Priority.LOW, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "13", description = "Задача номер 13", priority = Priority.LOW, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
    Task(id = "14", description = "Задача номер 14", priority = Priority.HIGH, deadline = 131333423, isDone = true, createDate = 0L, editDate = 0L),
    Task(id = "15", description = "Задача номер 15", priority = Priority.LOW, deadline = 0, isDone = false, createDate = 0L, editDate = 0L),
)