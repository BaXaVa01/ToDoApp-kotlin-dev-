package com.example.todoapp.menu



data class Task(
    val taskName: String,
    val description: String,
    var isSelected: Boolean = false,
    var isCompleted: Boolean = false

)