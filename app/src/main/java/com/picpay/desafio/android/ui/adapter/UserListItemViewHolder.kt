package com.picpay.desafio.android.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arch.toolkit.delegate.viewProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.User

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tvNameUser : AppCompatTextView by viewProvider(R.id.tv_item_name)
    private val tvUsernameUser : AppCompatTextView by viewProvider(R.id.tv_item_username)
    private val ciImageUser : AppCompatImageView by viewProvider(R.id.ci_item_picture)

    fun bind(user: User) {
        tvNameUser.text = user.name
        tvUsernameUser.text = user.username
        ciImageUser.load(user.img) {
            crossfade(1000)
            transformations(CircleCropTransformation())
            error(R.drawable.ic_round_account_circle)
            placeholder(R.drawable.ic_round_account_circle)
        }
    }
}