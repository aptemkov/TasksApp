package io.github.aptemkov.tasksapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.aptemkov.tasksapp.data.database.dao.TasksDao
import io.github.aptemkov.tasksapp.data.database.models.TaskDBO
import io.github.aptemkov.tasksapp.data.database.utils.Converters

/**
 * Данный класс необходим для взаимодействия с локальной базой данных
 *
 */
class TasksDatabase internal constructor(private val database: TasksRoomDatabase) {
    val tasksDao: TasksDao
        get() = database.tasksDao()
}

@Database(entities = [TaskDBO::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class TasksRoomDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}

fun TasksDatabase(applicationContext: Context): TasksDatabase {
    val newsRoomDatabase =
        Room.databaseBuilder(
            checkNotNull(applicationContext.applicationContext),
            TasksRoomDatabase::class.java,
            "tasks"
        ).build()
    return TasksDatabase(newsRoomDatabase)
}