package com.picpay.desafio.android.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.domain.User

class UserListDiffCallback() : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.username == newItem.username &&
                oldItem.img == newItem.img &&
                oldItem.name == newItem.name
    }
}