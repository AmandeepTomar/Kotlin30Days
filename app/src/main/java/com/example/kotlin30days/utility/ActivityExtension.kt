package com.example.kotlin30days.utility

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

internal fun <T : AppCompatActivity> AppCompatActivity.start(cls: Class<T>) {
    startActivity(Intent(this, cls))
}


internal fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

internal fun View.setVisible(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else View.INVISIBLE

}

internal fun View.setVisibleGone(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else View.GONE

}

internal fun View.showSnackBar(message:String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show();
}

fun ImageView.setImage(url: String){
    Glide.with(this).load(url).into(this)
}
