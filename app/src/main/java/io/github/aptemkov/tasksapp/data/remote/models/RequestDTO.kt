package io.github.aptemkov.tasksapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ElementRequest(
    val element: Element,
)

@Serializable
data class AllElementsRequest(
    val list: List<Element>,
)