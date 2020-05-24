package org.fansin.intellideck.di

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckRepository
import org.fansin.intellideck.deck.ui.DeckItemTouchHelperCallback
import org.fansin.intellideck.deck.ui.active.ActiveDeckAdapter
import org.fansin.intellideck.deck.ui.inactive.InactiveDeckAdapter
import javax.inject.Singleton

@Module
class DeckModule {

    @Singleton
    @Provides
    fun provideDeckObservable(): DeckObservable {
        return DeckObservable()
    }

    @Singleton
    @Provides
    fun provideDeckRepository(context: Context, deckObservable: DeckObservable): DeckRepository {
        return DeckRepository(context, deckObservable)
    }

    @Singleton
    @Provides
    fun provideActiveDeckAdapter(
        deckRepository: DeckRepository,
        deckObservable: DeckObservable
    ): ActiveDeckAdapter {
        return ActiveDeckAdapter(deckObservable, deckRepository.activeItems)
    }

    @Singleton
    @Provides
    fun provideInactiveDeckAdapter(
        deckRepository: DeckRepository,
        deckObservable: DeckObservable
    ): InactiveDeckAdapter {
        return InactiveDeckAdapter(deckObservable, deckRepository.inactiveItems)
    }

    @Singleton
    @Provides
    fun provideDeckItemTouchHelperCallback(
        deckObservable: DeckObservable
    ): DeckItemTouchHelperCallback {
        return DeckItemTouchHelperCallback(deckObservable)
    }

    @Singleton
    @Provides
    fun provideItemTouchHelper(
        deckItemTouchHelperCallback: DeckItemTouchHelperCallback
    ): ItemTouchHelper {
        return ItemTouchHelper(deckItemTouchHelperCallback)
    }
}
