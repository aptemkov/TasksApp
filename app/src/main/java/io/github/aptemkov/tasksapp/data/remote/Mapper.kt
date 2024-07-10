package io.github.aptemkov.tasksapp.data.remote

import io.github.aptemkov.tasksapp.data.remote.models.Element
import io.github.aptemkov.tasksapp.data.remote.models.Importance
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task

/**
 * Данный файл необходим для преобразования Element, приходящего с сервера,
 * в Task, используемый в приложении
 *
 */

fun Task.toElement(): Element {
    return Element(
        id = this.id,
        text = this.description,
        importance = this.priority.toImportance(),
        deadline = this.deadline,
        done = this.isDone,
        createdAt = this.createDate,
        changedAt = this.editDate,
        lastUpdatedBy = "test"
    )
}

fun Element.toTask(): Task {
    return Task(
        id = this.id,
        description = this.text,
        priority = this.importance.toPriority(),
        deadline = this.deadline ?: 0,
        isDone = this.done,
        createDate = this.createdAt,
        editDate = this.changedAt,
    )
}

fun Priority.toImportance(): Importance {
    return when(this) {
        Priority.LOW -> Importance.low
        Priority.DEFAULT -> Importance.basic
        Priority.HIGH -> Importance.important
    }
}

fun Importance.toPriority(): Priority {
    return when(this) {
         Importance.low -> Priority.LOW
         Importance.basic -> Priority.DEFAULT
         Importance.important -> Priority.HIGH
    }
}