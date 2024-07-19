package io.github.aptemkov.tasksapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aptemkov.tasksapp.data.database.models.TaskDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

//    @Query("SELECT * FROM tasks WHERE removed = 0 ORDER BY created_at DESC")
    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    suspend fun getAll(): List<TaskDBO>

//    @Query("SELECT * FROM tasks WHERE removed = 0 ORDER BY created_at DESC")
    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    fun observeAll(): Flow<List<TaskDBO>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: String): TaskDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tasks: List<TaskDBO>)

    @Query("UPDATE tasks SET done = :isDone WHERE id = :itemId")
    suspend fun changeIsDone(itemId: String, isDone: Boolean)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun removeById(taskId: String)

    @Query("DELETE FROM tasks")
    suspend fun clean()

//    @Query("UPDATE tasks SET removed = 1 WHERE id = :taskId")
//    suspend fun markRemoved(taskId: String)
}