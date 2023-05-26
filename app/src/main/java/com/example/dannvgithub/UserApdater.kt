package com.example.dannvgithub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dannvgithub.databinding.UserInfoLayoutBinding
import com.example.example.GitHubUserListResponse

class UserApdater: RecyclerView.Adapter<UserApdater.ViewHolder>() {

    var listUser = listOf<GitHubUserListResponse>()
    set(value) {
        listUser = value
        notifyDataSetChanged()
    }
    class ViewHolder(userLayout: UserInfoLayoutBinding) : RecyclerView.ViewHolder(userLayout.root) {
        fun onBind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserInfoLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind()
    }

    override fun getItemCount(): Int = listUser.size
}