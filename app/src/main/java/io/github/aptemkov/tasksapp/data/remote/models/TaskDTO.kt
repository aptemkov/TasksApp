package io.github.aptemkov.tasksapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Element (
    val id: String,
    val text: String,
    val files: List<String>? = null,
    val importance: Importance,
    val deadline: Long? = null,
    val done: Boolean,
    val color: String? = null,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("changed_at") val changedAt: Long,
    @SerialName("last_updated_by") val lastUpdatedBy: String
)

@Serializable
enum class Importance {
    low,
    basic,
    important
}