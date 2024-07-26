package io.github.aptemkov.tasksapp

import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import io.github.aptemkov.tasksapp.data.database.dao.TasksDao
import io.github.aptemkov.tasksapp.data.database.models.toTaskDBO
import io.github.aptemkov.tasksapp.data.repository.LocalTasksRepositoryImpl
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LocalTasksRepositoryImplTest {

    private lateinit var database: TasksDatabase
    private lateinit var tasksDao: TasksDao
    private lateinit var repository: LocalTasksRepositoryImpl

    @Before
    fun setUp() {
        tasksDao = mock(TasksDao::class.java)
        database = mock(TasksDatabase::class.java)
        `when`(database.tasksDao).thenReturn(tasksDao)
        repository = LocalTasksRepositoryImpl(database)
    }

    @Test
    fun `test getAllTasks`() = runBlocking {
        val tasks = listOf(
            Task(id ="1", description = "Task 1", priority = Priority.HIGH, isDone = false),
            Task(id ="2", description = "Task 2", priority = Priority.LOW, isDone = true),
            Task(id ="3", description = "Task 3", priority = Priority.DEFAULT, isDone = false),
        )
        `when`(tasksDao.observeAll()).thenReturn(flowOf(tasks.map { it.toTaskDBO() }))

        val result = repository.getAllTasks()

        result.collect { list ->
            assertEquals(3, list.size)
            assertEquals("Task 1", list[0].description)
        }
    }

    @Test
    fun `test addTasks`() = runBlocking {
        val task = Task(id ="4", description =  "Task 4", priority = Priority.DEFAULT, isDone = false)
        repository.addTasks(listOf(task))

        verify(tasksDao).insert(listOf(task.toTaskDBO()))
    }

    @Test
    fun `test removeTaskById`() = runBlocking {
        val taskId = "1"
        repository.removeTaskById(taskId)

        verify(tasksDao).removeById(taskId)
    }
}
