package io.github.aptemkov.tasksapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ElementResponse(
    val status: String,
    val element: Element,
    val revision: Int
)

@Serializable
data class AllElementsResponse(
    val status: String,
    val list: List<Element>,
    val revision: Int
)