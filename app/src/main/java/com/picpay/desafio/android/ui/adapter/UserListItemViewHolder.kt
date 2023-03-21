package com.picpay.desafio.android.ui.adapter

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arch.toolkit.delegate.viewProvider
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tvNameUser : AppCompatTextView by viewProvider(R.id.tv_item_name)
    private val tvUsernameUser : AppCompatTextView by viewProvider(R.id.tv_item_username)
    private val pbProgressBarUser : ProgressBar by viewProvider(
        R.id.pb_item_progress_bar_image
    )
    private val ciImageUser : CircleImageView by viewProvider(R.id.ci_item_picture)

    fun bind(user: User) {
        tvNameUser.text = user.name
        tvUsernameUser.text = user.username
        pbProgressBarUser.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(ciImageUser, object : Callback {
                override fun onSuccess() {
                    pbProgressBarUser.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    pbProgressBarUser.visibility = View.GONE
                }
            })
    }
}