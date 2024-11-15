package com.example.todoapp.DATA

import com.example.todoapp.menu.Task

class TaskRepository(private val taskDao: DAO) {

    suspend fun getTasks(): List<Task> {
        val entities = taskDao.getTasks()
        return entities.map {
            Task(
                taskName = it.taskName,
                description = it.description,
                isSelected = it.isSelected,
                isCompleted = it.isCompleted
            )
        }
    }

    suspend fun insertTask(task: Task) {
        val entity = TaskEntity(
            taskName = task.taskName,
            description = task.description,
            isSelected = task.isSelected,
            isCompleted = task.isCompleted
        )
        taskDao.insetTask(entity)
    }

    suspend fun deleteTask(task: Task){

    }
}