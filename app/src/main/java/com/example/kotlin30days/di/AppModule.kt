package com.example.kotlin30days.di

import com.example.kotlin30days.utility.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(myApp: MyApp)=myApp
}