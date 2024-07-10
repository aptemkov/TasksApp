package io.github.aptemkov.tasksapp.data.database.utils

import androidx.room.TypeConverter
import io.github.aptemkov.tasksapp.domain.models.Priority


/**
 * Данный класс необходим для преобразования Enum приоритета в строку и обратно
 *
 */
internal class Converters {
    @TypeConverter
    fun priorityFromString(value: String): Priority {
        return enumValueOf<Priority>(value)
    }

    @TypeConverter
    fun stringFromPriority(priority: Priority): String {
        return priority.name
    }
}
