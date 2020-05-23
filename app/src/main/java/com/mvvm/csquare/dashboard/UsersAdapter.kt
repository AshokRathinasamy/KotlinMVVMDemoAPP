package com.mvvm.csquare.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.databinding.RowItemTaskBinding

class UsersAdapter(private val viewModel: DashBoardFragViewModel) :
    ListAdapter<DataUserList, UsersAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    class TaskViewHolder private constructor(val binding: RowItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModels: DashBoardFragViewModel, realestate: DataUserList) {
            binding.viewModel = viewModels
            binding.itemValues = realestate
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowItemTaskBinding.inflate(layoutInflater, parent, false)
                return TaskViewHolder(binding)
            }
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<DataUserList>() {
    override fun areItemsTheSame(
        oldItem: DataUserList,
        newItem: DataUserList
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataUserList,
        newItem: DataUserList
    ): Boolean {
        return oldItem == newItem
    }

}