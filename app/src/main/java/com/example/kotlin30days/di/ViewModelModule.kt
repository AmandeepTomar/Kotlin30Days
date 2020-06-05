package com.example.kotlin30days.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin30days.factory.AppViewModelFactory
import com.example.kotlin30days.view.home.viewmodel.HomeViewModel
import com.example.kotlin30days.view.login.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    protected abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun bindHomeViewModel(homeViewModel: HomeViewModel):ViewModel

}