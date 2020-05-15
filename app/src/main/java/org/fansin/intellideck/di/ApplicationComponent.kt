package org.fansin.intellideck.di

import dagger.Component
import org.fansin.intellideck.ActiveCardsFragment
import org.fansin.intellideck.InactiveCardsFragment
import org.fansin.intellideck.MainActivity
import javax.inject.Singleton

@Component
@Singleton
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(inactiveCardsFragment: InactiveCardsFragment)

    fun inject(activeCardsFragment: ActiveCardsFragment)
}
