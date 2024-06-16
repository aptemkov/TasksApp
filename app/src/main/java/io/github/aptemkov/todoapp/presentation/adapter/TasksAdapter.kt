package io.github.aptemkov.todoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.aptemkov.todoapp.R
import io.github.aptemkov.todoapp.domain.models.Importance
import io.github.aptemkov.todoapp.domain.models.TodoItem

class TasksAdapter(
    private val onClick: (TodoItem) -> Unit,
) : ListAdapter<TodoItem, TasksAdapter.ViewHolder>(TaskDiffCallback) {

    class ViewHolder(view: View, val onClick: (TodoItem) -> Unit) : RecyclerView.ViewHolder(view) {

        private var currentTask: TodoItem? = null
        
        val description: TextView = view.findViewById(R.id.task_item_description)
        val importance: ImageView = view.findViewById(R.id.task_item_importance_icon)
        val deadline: TextView = view.findViewById(R.id.task_item_deadline)
        val isDone: ImageView = view.findViewById(R.id.task_item_is_done)

        init {
            view.setOnClickListener {
                currentTask?.let { onClick(it) }
            }
        }

        fun bind(task: TodoItem) {
            currentTask = task
            
            description.text = task.description

            when(task.deadline) {
                0L -> {
                    deadline.visibility = View.GONE
                }
                else -> {
                    deadline.visibility = View.VISIBLE
                    deadline.text = task.deadline.toDate()
                }
            }

            when(task.isDone) {
                true -> {
                    isDone.setImageResource(R.drawable.ic_checkbox_selected)
                }
                false -> {
                    if(task.importance == Importance.HIGH) {
                        isDone.setImageResource(R.drawable.ic_checkbox_important_unselected)
                    } else {
                        isDone.setImageResource(R.drawable.ic_checkbox_unselected)
                    }
                }
            }

            when(task.importance) {
                Importance.LOW -> {
                    importance.visibility = View.GONE
                }
                Importance.MEDIUM -> {
                    importance.visibility = View.VISIBLE
                    importance.setImageResource(R.drawable.ic_important_task)
                }
                Importance.HIGH -> {
                    importance.visibility = View.VISIBLE
                    importance.setImageResource(R.drawable.ic_unimportant_task)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.task_item, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val task = getItem(position)
        viewHolder.bind(task)
    }
}
