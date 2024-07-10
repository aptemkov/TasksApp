package io.github.aptemkov.tasksapp.presentation.task

/**
 * Данный класс необходим для удобной передачи аргументов в экран TaskScreen
 *
 */

sealed class TasksScreenArgument() {
    data class TaskEdit(val id: String) : TasksScreenArgument()
    data class TaskDetails(val id: String) : TasksScreenArgument()
    data object TaskNew : TasksScreenArgument()
}

fun getTaskScreenArgument(id: String?, isEdit: Boolean) =
    if (!id.isNullOrEmpty()) {
        if (isEdit) {
            TasksScreenArgument.TaskEdit(id)
        } else {
            TasksScreenArgument.TaskDetails(id)
        }
    } else {
        TasksScreenArgument.TaskNew
    }

fun TasksScreenArgument.getId(): String? {
    var id: String? = null
    if (this is TasksScreenArgument.TaskEdit) id = this.id
    else if (this is TasksScreenArgument.TaskDetails) id = this.id

    return id
}