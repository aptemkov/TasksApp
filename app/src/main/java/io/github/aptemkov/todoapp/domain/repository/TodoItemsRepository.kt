package io.github.aptemkov.todoapp.domain.repository

interface TodoItemsRepository {

    fun getTask(id: String)

    fun addTask(task: String)

    fun getAllTasks()

}