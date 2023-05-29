package com.example.dannvgithub

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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

    fun createDialog(context: Context, content: String = "Loading ..."): AlertDialog {
       return AlertDialog.Builder(context)
            .setMessage(content) // Specifying a listener allows you to take an action before dismissing the dialog.
            .setNegativeButton(R.string.no, null)
            .setIcon(R.drawable.ic_dialog_alert)
            .show()
    }
}