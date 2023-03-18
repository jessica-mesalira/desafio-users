package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.tv_item_name.text = user.name
        itemView.tv_item_username.text = user.username
        itemView.pb_item_progress_bar_image.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.ci_item_picture, object : Callback {
                override fun onSuccess() {
                    itemView.pb_item_progress_bar_image.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.pb_item_progress_bar_image.visibility = View.GONE
                }
            })
    }
}