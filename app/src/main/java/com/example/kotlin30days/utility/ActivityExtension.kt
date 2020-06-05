package com.example.kotlin30days.utility

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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