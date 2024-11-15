package com.example.todoapp.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.TaskAdapter
import com.example.todoapp.databinding.ActivityMainBinding
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.todoapp.DATA.TaskDb
import com.example.todoapp.DATA.TaskRepository
import com.example.todoapp.R
import com.example.todoapp.TaskView.TaskDetailView
import com.example.todoapp.addtask.AddTaskBottomSheet
import com.example.todoapp.addtask.AddTaskViewModel


import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), RecyclerViewUpdater, DataModelViewUpdater {

    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: FloatingActionButton
    private lateinit var viewModel: AddTaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        initDB()
        downloadDB()
        initRecyclerView()
        initVariables()
        initListener()

    }

    private fun downloadDB() {

        viewModel.getTasks()

    }

    private fun initDB(){
        val db = Room.databaseBuilder(this, TaskDb::class.java, "task_db").build()
        val dao = db.dao
        val repository = TaskRepository(dao)
        viewModel = AddTaskViewModel(repository, this)

    }

    private fun initVariables() {
        addButton = findViewById(R.id.fab_AddTasks)
    }

    private fun initListener() {

        addButton.setOnClickListener {

            val addTaskBottomSheet = AddTaskBottomSheet(this, this)
            vibratePhone(200)

            addTaskBottomSheet.show(supportFragmentManager, "AddTaskBottom")

            Log.d("DebugPrintln", TaskProvider.taskList.size.toString())

        }
    }


    private fun initRecyclerView() {

        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val decoration = DividerItemDecoration(this, manager.orientation)


        binding.rvFirstTasks.layoutManager = manager


        binding.rvFirstTasks.adapter =
            TaskAdapter(TaskProvider.taskList, { task -> onItemSelected(task) })
        binding.rvFirstTasks.addItemDecoration(decoration)
    }


    fun onItemSelected(task: Task) {

        Toast.makeText(this, task.taskName, Toast.LENGTH_SHORT).show()
        vibratePhone(200)
        val taskDetail = TaskDetailView(task)
        taskDetail.show(supportFragmentManager, "TaskDetail")

    }

    private fun vibratePhone(miliseconds: Long) {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Para versiones de Android 8.0 (API 26) y superiores
            val vibrationEffect =
                VibrationEffect.createOneShot(miliseconds, VibrationEffect.EFFECT_DOUBLE_CLICK)
            vibrator.vibrate(vibrationEffect)
        } else {
            // Para versiones anteriores a Android 8.0
            vibrator.vibrate(miliseconds) // Vibrar por 500 milisegundos
        }
    }

    override fun updateRV() {
        binding.rvFirstTasks.adapter?.notifyItemInserted(TaskProvider.taskList.size)
    }

    override fun InsertData(task: Task) {
        viewModel.insertTask(task)

    }

    override fun getData() {
        viewModel.getTasks()
    }


}