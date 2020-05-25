package org.fansin.intellideck.di

import dagger.Component
import org.fansin.intellideck.MainActivity
import org.fansin.intellideck.deck.ui.AddCardsFragment
import org.fansin.intellideck.deck.ui.CardsFragment
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

    fun inject(addCardsFragment: AddCardsFragment)

    fun inject(cardsFragment: CardsFragment)
}
