package com.kashapovrush.cardbinrequest.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.cardbinrequest.R
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain

class CardInfoAdapter: ListAdapter<CardInfoMain, CardInfoAdapter.CardInfoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )

        return CardInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardInfoViewHolder, position: Int) {
        val cardInfo = getItem(position)
        holder.tvNumber.text = cardInfo.inputNumber
    }

    class CardInfoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvNumber = view.findViewById<TextView>(R.id.tv_number_item)
    }
}