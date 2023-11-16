package com.kashapovrush.cardbinrequest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain

class DiffCallback: DiffUtil.ItemCallback<CardInfoMain>() {
    override fun areItemsTheSame(oldItem: CardInfoMain, newItem: CardInfoMain): Boolean {
        return oldItem.inputNumber == newItem.inputNumber
    }

    override fun areContentsTheSame(oldItem: CardInfoMain, newItem: CardInfoMain): Boolean {
        return oldItem == newItem
    }
}