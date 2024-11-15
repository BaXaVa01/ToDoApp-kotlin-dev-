package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.db_entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDb : RoomDatabase(){
    abstract val dao: DAO
}