package com.example.kotlin30days.di

import com.example.kotlin30days.view.home.fragment.DetailsFragment
import com.example.kotlin30days.view.home.fragment.HomeFragment
import com.example.kotlin30days.view.home.fragment.ListofReposFragment
import com.example.kotlin30days.view.login.LoginFragment
import com.example.kotlin30days.view.login.RegisterFragment
import com.example.kotlin30days.view.settings.SettingFragment
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
    abstract fun bindSettingFragment(): SettingFragment

    @ContributesAndroidInjector
    abstract fun bindRepoListFragment(): ListofReposFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment


}