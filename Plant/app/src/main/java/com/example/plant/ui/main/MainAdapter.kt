package com.example.plant.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.data.userpojo.User
import com.example.plant.databinding.ActivityMainBinding
import com.example.plant.databinding.UserItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: UserItemBinding
    private var user:ArrayList<User> = arrayListOf()

    inner class MainViewHolder(itemView:UserItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var binder:UserItemBinding?=null

        init {
            binder = itemView

        }

        fun bind(user: User?) {
             binder?.user= user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(user[position])
    }

    override fun getItemCount(): Int = user.size


    fun setUser(mUser:ArrayList<User>){
        user = mUser
        notifyDataSetChanged()
    }
}