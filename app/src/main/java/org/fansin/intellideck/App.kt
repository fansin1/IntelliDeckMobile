package org.fansin.intellideck

import android.app.Application
import org.fansin.intellideck.di.ApplicationComponent
import org.fansin.intellideck.di.ApplicationModule
import org.fansin.intellideck.di.DaggerApplicationComponent
import org.fansin.intellideck.di.DeckModule

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .deckModule(DeckModule())
            .build()
    }
}
