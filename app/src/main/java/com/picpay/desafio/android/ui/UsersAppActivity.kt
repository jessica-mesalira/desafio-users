package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.arch.toolkit.delegate.viewProvider
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class UsersAppActivity : AppCompatActivity(R.layout.activity_users_app) {

    private val recyclerView: RecyclerView by viewProvider(R.id.rv_app_user_list)
    private val progressBar: ProgressBar by viewProvider(R.id.pb_user_list_progress)
    private val adapter: UserListAdapter = UserListAdapter()
    private val usersViewModel: UsersAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupObservables()
        usersViewModel.getUsers()
    }

    private fun setupObservables() {
        usersViewModel.userLiveData.observe(this) {
            showLoading { progressBar.visibility = View.VISIBLE }
            data {
                progressBar.visibility = View.GONE
                adapter.submitList(it)
            }
            error {e ->
                setErrorState(e)
            }
        }
    }

    private fun setErrorState(e: Throwable) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        val message = if (e is UnknownHostException) getString(R.string.error_no_connection)
            else getString(R.string.error)

        Toast.makeText(this@UsersAppActivity, message, Toast.LENGTH_LONG).show()
    }

    private fun setupViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
