package io.github.aptemkov.tasksapp.presentation.task

// Честно говоря, нигде не видел такого способа переиспользования экрана.
// Здесь я просто для удобства сделал такой аргумент для TaskScreen, чтобы не передавать
// в экран id: String и isEdit: Boolean, а потом ещё и обрабатывать внутри него.

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