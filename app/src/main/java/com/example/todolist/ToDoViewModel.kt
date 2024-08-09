package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<String>>()
    val tasks: LiveData<List<String>> get() = _tasks

    init {
        _tasks.value = listOf()  // Initialize with an empty list
    }

    fun addTask(task: String) {
        _tasks.value = _tasks.value?.plus(task)
    }

    fun removeTask(task: String) {
        _tasks.value = _tasks.value?.minus(task)
    }

    fun completeTask(task: String) {
        // Implement task completion logic
    }
}
