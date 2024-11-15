package com.example.todoapp.TaskView

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import com.example.todoapp.menu.domain_entity.Task

/**
 * Representa a los animales del zoo
 *
 * @property name Nombre del animal
 * @property type Clasificación taxonomica del animal
 * @property age Edad en meses del animal
 *
 * @author James Revelo
 * @constructor Crea un nuevo animal
 */

class TaskDetailView(task: Task): DialogFragment() {

    private val taskHere: Task = task

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_task_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val transition = Slide(Gravity.BOTTOM) // Deslizar desde abajo
        transition.duration = 300 // Duración de la animación en milisegundos

        enterTransition = transition
        exitTransition = transition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        var taskName = view.findViewById<TextView>(R.id.tv_activityName)
        var taskDescription = view.findViewById<TextView>(R.id.tv_descriptionTask)
        var taskState = view.findViewById<CardView>(R.id.cvIsCompleteTask)

        taskName.text = taskHere.taskName
        taskDescription.text = taskHere.description
        taskDescription.movementMethod = ScrollingMovementMethod()

        val color = if(taskHere.isCompleted){
            ContextCompat.getColor(requireContext(), R.color.complete_tasks)

        } else ContextCompat.getColor(requireContext(), R.color.non_complete_tasks)

        taskState.setBackgroundColor(color)

        taskState.setOnClickListener{

            val color = if(taskHere.isCompleted){
                ContextCompat.getColor(requireContext(), R.color.non_complete_tasks)

            } else ContextCompat.getColor(requireContext(), R.color.complete_tasks)
            taskHere.isCompleted = !taskHere.isCompleted
            taskState.setBackgroundColor(color)
            // TODO: Deberia de al cambiar el color, tambien guardar en la base de datos que la tarea no esta completa aun
        }



    }

}