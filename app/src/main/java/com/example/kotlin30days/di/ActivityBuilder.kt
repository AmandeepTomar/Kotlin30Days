package com.example.kotlin30days.di

import com.example.kotlin30days.view.home.HomeActivity
import com.example.kotlin30days.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashScreen() : SplashActivity

    @ContributesAndroidInjector(modules = [HomeFragmentBuilder::class])
    abstract fun bindHomeScreen() :HomeActivity


}