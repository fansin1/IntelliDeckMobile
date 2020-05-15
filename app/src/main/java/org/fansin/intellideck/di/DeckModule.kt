package org.fansin.intellideck.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.deck.active.ActiveDeckAdapter
import org.fansin.intellideck.deck.DeckRepository
import org.fansin.intellideck.deck.inactive.InactiveDeckAdapter
import javax.inject.Singleton

@Module
class DeckModule {

    @Singleton
    @Provides
    fun provideDeckRepository(context: Context): DeckRepository {
        return DeckRepository(context)
    }

    @Singleton
    @Provides
    fun provideActiveDeckAdapter(deckRepository: DeckRepository): ActiveDeckAdapter {
        return ActiveDeckAdapter(
            deckRepository
        )
    }

    @Singleton
    @Provides
    fun provideInactiveDeckAdapter(deckRepository: DeckRepository): InactiveDeckAdapter {
        return InactiveDeckAdapter(
            deckRepository
        )
    }
}
