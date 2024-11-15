package com.example.todoapp.DATA

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int ?= null,
    val taskName: String,
    val description: String,
    var isSelected: Boolean = false,
    var isCompleted: Boolean = false
)

