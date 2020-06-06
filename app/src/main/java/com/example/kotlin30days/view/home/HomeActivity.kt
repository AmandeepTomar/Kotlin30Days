package com.example.kotlin30days.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlin30days.R
import com.example.kotlin30days.databinding.ActivityHomeBinding
import com.example.kotlin30days.utility.AppPref
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(),HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var appPref: AppPref

    lateinit var bindingHomeActivity: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHomeActivity=DataBindingUtil.setContentView(this,R.layout.activity_home)




    }

    override fun androidInjector() = dispatchingAndroidInjector


    override fun onBackPressed() {

        super.onBackPressed()
    }
}