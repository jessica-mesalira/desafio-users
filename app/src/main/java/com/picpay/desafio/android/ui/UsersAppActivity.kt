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

class UsersAppActivity : AppCompatActivity(R.layout.activity_users_app) {

    private val recyclerView: RecyclerView by viewProvider(R.id.rv_app_user_list)
    private val progressBar: ProgressBar by viewProvider(R.id.pb_user_list_progress)
    private val adapter: UserListAdapter = UserListAdapter()
    private val usersViewModel: UsersAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupObservables()
    }

    private fun setupObservables() {
        usersViewModel.getUsers().observe(this) {
            data {
                progressBar.visibility = View.GONE
                adapter.submitList(it)
            }
            error(observer = ::setErrorState )
        }
    }

    private fun setErrorState() {
        val message = getString(R.string.error)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@UsersAppActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        progressBar.visibility = View.VISIBLE
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
