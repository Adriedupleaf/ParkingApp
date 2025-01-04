package com.example.app3.di

import com.example.app3.utils.DefaultUIErrorHandler
import com.example.app3.utils.UIErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindings {
    @Binds
    abstract fun bindsUiErrorHandler(impl: DefaultUIErrorHandler): UIErrorHandler
}