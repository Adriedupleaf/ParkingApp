package com.example.data.di

import androidx.multidex.BuildConfig
import com.example.data.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppConfigurationModule {
    @Provides
    internal fun providesConfiguration(): Configuration = if (BuildConfig.DEBUG) {Configuration.Debug} else Configuration.Production
}
