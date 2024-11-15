package com.example.todoapp.DATA

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.menu.Task

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDb : RoomDatabase(){
    abstract val dao: DAO
}