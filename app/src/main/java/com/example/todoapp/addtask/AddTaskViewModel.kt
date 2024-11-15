package com.example.todoapp.addtask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.DATA.TaskRepository
import com.example.todoapp.menu.RecyclerViewUpdater
import com.example.todoapp.menu.Task
import com.example.todoapp.menu.TaskProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddTaskViewModel(private val taskRepository: TaskRepository, reloadRV: RecyclerViewUpdater) : ViewModel()  {

    var reloadData = reloadRV

    fun insertTask(task: Task){
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

     fun getTasks()  {

        viewModelScope.launch{
            val listaTareas = taskRepository.getTasks()

            for(tarea in listaTareas){
                TaskProvider.taskList.add(tarea)
            }
            reloadDataBase()
        }
     }

    fun reloadDataBase(){
        reloadData.updateRV()
    }


}

