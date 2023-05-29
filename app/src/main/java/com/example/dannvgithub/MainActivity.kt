package com.example.dannvgithub

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.example.dannvgithub.api.GithubApi
import com.example.dannvgithub.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val mViewModel: MainActivityViewModel by viewModels()
    var mUserApdater: UserApdater = UserApdater()
    var mDialog: AlertDialog? = null
    var isFirstSetup = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()
        mViewModel.isLoading.onEach {isLoading ->
            binding.apply {
                if (isLoading) {
                    mDialog = Utils.createDialog(this@MainActivity)
                    mDialog?.show()
                } else {
                    mDialog?.dismiss()
                    if (mViewModel.listUser.size <= 0) {
                        mDialog = Utils.createDialog(this@MainActivity, "Đã hết danh sách")
                        mDialog?.show()
                    }
                    mUserApdater.listUser.addAll(mViewModel.listUser)
                    mUserApdater.notifyDataSetChanged()
                }
            }
        }.launchIn(lifecycle.coroutineScope)

        mViewModel.getListUser()

        binding.apply {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (! recyclerView.canScrollVertically(1)){ //1 for down
                        mViewModel.getListUser()
                    }
                }
            })
        }
    }

    fun initRecycleView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = mUserApdater
        }
    }
}