package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil

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