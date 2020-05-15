package org.fansin.intellideck.di

import dagger.Component
import org.fansin.intellideck.deck.active.ActiveCardsFragment
import org.fansin.intellideck.deck.inactive.InactiveCardsFragment
import org.fansin.intellideck.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        DeckModule::class
    ]
)
@Singleton
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(inactiveCardsFragment: InactiveCardsFragment)

    fun inject(activeCardsFragment: ActiveCardsFragment)
}
