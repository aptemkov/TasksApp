package io.github.aptemkov.tasksapp.app.providers

interface ResourceProvider {
    fun getString(resId: Int, vararg params: String): String
}