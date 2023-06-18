package com.kashapovrush.cardbinrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.cardbinrequest.databinding.ListItemBinding

class ListHistoryAdapter: ListAdapter <ListHistory, ListHistoryAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(listHistory: ListHistory) = with(binding) {
            listItemHistory.text = listHistory.listItem
        }

        companion object {
            fun create(parent: ViewGroup) : ItemHolder {
                return ItemHolder(ListItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<ListHistory>() {
        override fun areItemsTheSame(oldItem: ListHistory, newItem: ListHistory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListHistory, newItem: ListHistory): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}