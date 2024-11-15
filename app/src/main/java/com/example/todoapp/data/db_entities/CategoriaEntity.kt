package com.example.todoapp.data.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
class CategoriaEntity {
    @PrimaryKey
    val id: Int ?= null


    val idTask: Int ?= null

}