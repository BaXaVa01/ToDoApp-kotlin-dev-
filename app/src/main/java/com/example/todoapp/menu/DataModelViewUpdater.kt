package com.example.todoapp.menu

import com.example.todoapp.menu.domain_entity.Task

interface DataModelViewUpdater {
    fun InsertData(task: Task)
    fun getData()
}