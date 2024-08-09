package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: List<Task>,
    private val updateTask: (Task) -> Unit,
    private val deleteTask: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskCompleted.isChecked = task.completed
        holder.taskCompleted.setOnCheckedChangeListener { _, isChecked ->
            task.completed = isChecked
            updateTask(task)
        }
        holder.btnDelete.setOnClickListener {
            deleteTask(task)
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.task_name)
        val taskCompleted: CheckBox = itemView.findViewById(R.id.task_completed)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)
    }
}
