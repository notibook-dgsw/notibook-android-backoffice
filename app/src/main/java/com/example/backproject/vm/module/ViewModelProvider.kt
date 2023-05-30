package com.example.backproject.vm.module

import com.example.backproject.vm.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelProvider {

    @Singleton
    @Provides
    fun provideMainViewModel() : MainViewModel {
        return MainViewModel()
    }

}