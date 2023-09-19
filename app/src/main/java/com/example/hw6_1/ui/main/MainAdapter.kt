package com.example.hw6_1.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.hw6_1.R
import com.example.hw6_1.databinding.ItemTaskBinding
import com.example.hw6_1.model.TaskModel

class MainAdapter(
    val onClickItem: (position: Int) -> Unit,
    val onLongClickItem: (position: Int) -> Unit
) : Adapter<MainAdapter.ViewHolder>() {

    private var list = ArrayList<TaskModel>()

    fun setDataInList(list: List<TaskModel>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(taskModel: TaskModel) {
            binding.cbTask.text = taskModel.title
            binding.cbTask.isChecked = taskModel.state
            if (binding.cbTask.isChecked) {
                binding.cbTask.setTextColor(R.color.grey)
            }
            itemView.setOnClickListener { onClickItem(adapterPosition) }
            itemView.setOnLongClickListener {
                onLongClickItem(adapterPosition)
                true
            }
        }
    }
}