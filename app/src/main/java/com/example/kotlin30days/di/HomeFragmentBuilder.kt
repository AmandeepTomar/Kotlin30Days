package com.example.kotlin30days.di

import com.example.kotlin30days.view.home.fragment.HomeFragment
import com.example.kotlin30days.view.login.LoginFragment
import com.example.kotlin30days.view.login.RegisterFragment
import com.example.kotlin30days.view.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindSettingFragment(): SettingsFragment


}