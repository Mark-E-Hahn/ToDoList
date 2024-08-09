package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskListView: ListView = findViewById(R.id.taskListView)
        val taskInput: EditText = findViewById(R.id.taskInput)
        val addButton: Button = findViewById(R.id.addButton)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        taskListView.adapter = adapter

        viewModel.tasks.observe(this, Observer { tasks ->
            adapter.clear()
            adapter.addAll(tasks)
            adapter.notifyDataSetChanged()
        })

        addButton.setOnClickListener {
            val task = taskInput.text.toString()
            if (task.isNotEmpty()) {
                viewModel.addTask(task)
                taskInput.text.clear()
            }
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            val task = adapter.getItem(position)
            task?.let {
                viewModel.completeTask(it)
            }
        }

        taskListView.setOnItemLongClickListener { _, _, position, _ ->
            val task = adapter.getItem(position)
            task?.let {
                viewModel.removeTask(it)
            }
            true
        }
    }
}
