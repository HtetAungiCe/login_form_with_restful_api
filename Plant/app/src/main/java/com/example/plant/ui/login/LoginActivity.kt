package com.example.plant.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plant.R
import com.example.plant.databinding.ActivityLoginBinding
import com.example.plant.network.LoginApi
import com.example.plant.ui.main.MainActivity
import com.example.plant.viewmodelfactory.ViewModelFactory
import com.example.plant.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.isLoading = false
        viewModel = ViewModelProvider(
            this, ViewModelFactory(LoginApi.create())
        ).get(LoginViewModel::class.java)

        binding.btnLogIn.setOnClickListener {

            binding.isLoading = true
            val email: String = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.getCurrentUser(email,password).observe(this, Observer { currentUser ->
                binding.tvVersion.text = currentUser.email
            })

            viewModel.getResult().observe(this, Observer { result ->
                if (!result) {
                    binding.isLoading = false
                    Toast.makeText(this, "Login Fail!!!", Toast.LENGTH_SHORT).show()
                } else {
                    binding.isLoading = false
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("token",viewModel.getToken())
                    d("token_login",viewModel.getToken())
                    startActivity(intent)
                }
            })


        }

    }


}