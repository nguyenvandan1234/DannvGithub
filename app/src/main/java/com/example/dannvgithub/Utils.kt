package com.example.dannvgithub

import android.util.Log
import androidx.databinding.BindingAdapter
import coil.load
import de.hdodenhof.circleimageview.CircleImageView

object Utils {
    @JvmStatic @BindingAdapter("imageUrl")
    fun loadImage(view: CircleImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            view.load(url) {
                listener(onSuccess = { _, _ ->
                }, onError = { errr, ee ->
                })
            }
        }
    }

}