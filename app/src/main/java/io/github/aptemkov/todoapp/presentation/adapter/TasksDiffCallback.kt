package io.github.aptemkov.todoapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.aptemkov.todoapp.domain.models.TodoItem

object TaskDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }
}