package com.example.todoapp.menu.domain_entity



data class Task(
    val taskName: String,
    val description: String,
    var isSelected: Boolean = false,
    var isCompleted: Boolean = false

)