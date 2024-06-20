package io.github.aptemkov.tasksapp.data.repository

import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import io.github.aptemkov.tasksapp.presentation.home.testList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(): TasksRepository {
    private val _tasks: MutableList<Task> = testList.toMutableList()
    override val tasks: List<Task> get() = _tasks

    override fun getAllTasks(): List<Task> {
        return tasks
    }

    override fun getTaskById(id: String): Task? {
        val task = tasks.find { it.id == id }
        return task
    }

    override fun addTask(task: Task): Boolean {
        _tasks.add(index = 0, element = task)
        return true
    }
}