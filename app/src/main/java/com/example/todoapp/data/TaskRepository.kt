package com.example.todoapp.data

import com.example.todoapp.data.db_entities.TaskEntity
import com.example.todoapp.menu.domain_entity.Task

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