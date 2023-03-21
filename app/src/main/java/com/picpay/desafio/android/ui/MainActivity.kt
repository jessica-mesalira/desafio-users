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
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val recyclerView: RecyclerView by viewProvider(R.id.rv_main_contact_list)
    private val progressBar: ProgressBar by viewProvider(R.id.pb_user_list_progress)
    private val adapter: UserListAdapter = UserListAdapter()
    private val usersViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        usersViewModel.getUsers().observe(this) {
            data{
                adapter.submitList(it)
            }
        }
    }

    private fun setupViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }



}

//override fun onFailure(call: Call<List<User>>, t: Throwable) {
//    val message = getString(R.string.error)
//
//    progressBar.visibility = View.GONE
//    recyclerView.visibility = View.GONE
//
//    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//        .show()
//}
//
//override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//    progressBar.visibility = View.GONE
//
//    adapter.submitList(response.body())
//}
