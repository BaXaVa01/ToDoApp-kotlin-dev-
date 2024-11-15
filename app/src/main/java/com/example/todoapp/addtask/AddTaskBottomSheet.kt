package com.example.todoapp.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.menu.DataModelViewUpdater
import com.example.todoapp.menu.RecyclerViewUpdater


import com.example.todoapp.menu.domain_entity.Task
import com.example.todoapp.menu.TaskProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



class AddTaskBottomSheet(reloadRV: RecyclerViewUpdater, insertDB:DataModelViewUpdater) : BottomSheetDialogFragment() {

    var reload: RecyclerViewUpdater = reloadRV
    var insertIn: DataModelViewUpdater = insertDB


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskNameInput = view.findViewById<EditText>(R.id.task_name_input)
        val taskDescriptionInput = view.findViewById<EditText>(R.id.task_description_input)
        val addButton = view.findViewById<Button>(R.id.add_task_button)

        addButton.setOnClickListener {
            val taskName = taskNameInput.text.toString()
            val taskDescription = taskDescriptionInput.text.toString()

            // Aquí deberías validar los datos antes de añadir la tareaXX
             if (taskName.isNotEmpty()) {
                TaskProvider.taskList.add(Task(taskName, taskDescription))

                updateRV()
                insertInDB(Task(taskName, taskDescription))
                getData()
                dismiss()


            } else {
                Toast.makeText(context, "Please enter a task name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRV() {
        reload.updateRV()
    }
    private fun insertInDB(task: Task){
        insertIn.InsertData(task)
    }
    private fun getData(){
        insertIn.getData()
    }



}
