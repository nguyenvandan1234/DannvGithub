package com.example.dannvgithub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.dannvgithub.databinding.UserInfoLayoutBinding
import com.example.example.GitHubUserListResponse
import de.hdodenhof.circleimageview.CircleImageView

class UserApdater: RecyclerView.Adapter<UserApdater.ViewHolderUser>() {

    var listUser = mutableListOf<GitHubUserListResponse>()
    class ViewHolderUser(var userLayout: UserInfoLayoutBinding) : RecyclerView.ViewHolder(userLayout.root) {
        fun onBind(gitHubUserListResponse: GitHubUserListResponse) {
            userLayout.userInfo = gitHubUserListResponse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserInfoLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolderUser(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        holder.onBind(listUser[position])
    }
}