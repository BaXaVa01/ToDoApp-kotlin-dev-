package com.example.todoapp.DATA

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.menu.Task

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetTask(task: TaskEntity)



    @Query("SELECT * FROM TaskEntity")
    suspend fun getTasks(): List<TaskEntity>

//    @Query("DELETE FROM TaskEntity WHERE")
    @Query("DELETE FROM TaskEntity WHERE id = :id ")
    suspend fun deleteTask(id: Int )
}