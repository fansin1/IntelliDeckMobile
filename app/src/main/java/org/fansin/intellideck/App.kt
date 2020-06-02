package org.fansin.intellideck

import android.app.Application
import org.fansin.intellideck.di.*

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    fun onMainActivityCreated(activity: MainActivity) {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .activityModule(ActivityModule(activity))
            .deckModule(DeckModule())
            .build()
    }
}
