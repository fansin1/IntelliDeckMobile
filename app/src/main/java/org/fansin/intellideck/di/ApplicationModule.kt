package org.fansin.intellideck.di

import android.content.Context
import android.os.Vibrator
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.deck.domain.ConnectionObservable
import javax.inject.Singleton

@Module
class ApplicationModule(@get:Provides val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideAppConfig(): AppConfig {
        return AppConfig()
    }

    @Provides
    @Singleton
    fun provideVibrator(context: Context): Vibrator {
        return context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    @Provides
    @Singleton
    fun provideConnectionObservable(context: Context): ConnectionObservable {
        return ConnectionObservable(context)
    }
}
