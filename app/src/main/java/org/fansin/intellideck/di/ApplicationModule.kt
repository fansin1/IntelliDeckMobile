package org.fansin.intellideck.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.AppConfig
import javax.inject.Singleton

@Module
class ApplicationModule(@get:Provides val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideAppConfig(context: Context): AppConfig {
        return AppConfig(context)
    }
}
