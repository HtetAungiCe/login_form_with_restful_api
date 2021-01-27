package com.example.plant.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plant.R
import com.example.plant.databinding.ActivityMainBinding
import com.example.plant.network.LoginApi
import com.example.plant.network.UserApi
import com.example.plant.viewmodelfactory.MainViewModelFactory
import com.example.plant.viewmodelfactory.ViewModelFactory
import com.example.plant.viewmodels.LoginViewModel
import com.example.plant.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:MainAdapter
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.isLoading = true
        adapter = MainAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = layoutManager
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(UserApi.create())
        ).get(MainViewModel::class.java)
        val token = intent.getStringExtra("token")
        Log.d("token_main", ""+token)
        viewModel.getUser(token!!).observe(this, Observer { user ->
            binding.isLoading = false
            adapter.setUser(user)
            Log.d("token_main", ""+token)
        })
    }
}