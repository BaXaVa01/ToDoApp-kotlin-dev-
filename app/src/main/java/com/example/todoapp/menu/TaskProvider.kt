package com.example.todoapp.menu

import com.example.todoapp.menu.domain_entity.Task

class TaskProvider {
    companion object {
        val taskList: MutableList<Task> = mutableListOf(
            Task("Hacer algebra lineal", "none", true, true),
            Task("Terminar base de datos",  " none" ,  true, false),
            Task("Terminar POB",  " none", true, false)
        )
    }
}