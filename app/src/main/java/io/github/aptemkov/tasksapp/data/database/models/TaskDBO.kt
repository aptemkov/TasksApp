package io.github.aptemkov.tasksapp.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task

@Entity(tableName = "tasks")
data class TaskDBO(
    @PrimaryKey val id: String,
    @ColumnInfo("text") val description: String,
    @ColumnInfo("importance") val priority: Priority,
    @ColumnInfo("deadline") val deadline: Long = 0L,
    @ColumnInfo("done") val isDone: Boolean,
    @ColumnInfo("removed") val isRemoved: Boolean = false,
    @ColumnInfo("created_at") val createDate: Long = 0L,
    @ColumnInfo("changed_at") val editDate: Long = 0L,
)

fun Task.toTaskDBO(): TaskDBO {
    return TaskDBO(
        id = this.id,
        description = this.description,
        priority = this.priority,
        deadline = this.deadline,
        isDone = this.isDone,
//        isRemoved = this.isRemoved,
        createDate = this.createDate,
        editDate = this.editDate,
    )
}

fun TaskDBO.toTask(): Task {
    return Task(
        id = this.id,
        description = this.description,
        priority = this.priority,
        deadline = this.deadline,
        isDone = this.isDone,
//        isRemoved = this.isRemoved,
        createDate = this.createDate,
        editDate = this.editDate,
    )
}