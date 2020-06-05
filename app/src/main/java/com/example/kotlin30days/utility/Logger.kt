package com.example.kotlin30days.utility

import android.util.Log
import com.example.kotlin30days.BuildConfig

object Logger {

    fun setLog(tag:String="30DaysLogs",message:String){
        if (BuildConfig.DEBUG){
            Log.e(tag,message)
        }
    }
}