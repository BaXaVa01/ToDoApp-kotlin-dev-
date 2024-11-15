package com.example.todoapp.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskPrimaryBinding
import com.example.todoapp.menu.Task


class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemTaskPrimaryBinding.bind(view)

    fun render(taskModel: Task, onClickListener: (Task) -> Unit) {

        binding.tvActivityName.text = taskModel.taskName

        val color = if (taskModel.isCompleted){
            ContextCompat.getColor(itemView.context, R.color.complete_tasks)
        } else {
            ContextCompat.getColor(itemView.context, R.color.non_complete_tasks)
        }

        binding.lyIsCompleted.setBackgroundColor(color)

        itemView.setOnClickListener{
            onClickListener(taskModel)
        }
    }
}
