package org.fansin.intellideck.di

import android.content.Context
import android.os.Vibrator
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckRepository
import org.fansin.intellideck.deck.ui.AddCardsAdapter
import org.fansin.intellideck.deck.ui.DeckAdapter
import org.fansin.intellideck.deck.ui.DeckItemTouchHelperCallback
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
    ): DeckAdapter {
        return DeckAdapter(
            deckObservable,
            deckRepository.activeItems
        )
    }

    @Singleton
    @Provides
    fun provideDeckItemTouchHelperCallback(
        appConfig: AppConfig,
        vibrator: Vibrator,
        deckObservable: DeckObservable
    ): DeckItemTouchHelperCallback {
        return DeckItemTouchHelperCallback(appConfig, vibrator, deckObservable)
    }

    @Singleton
    @Provides
    fun provideItemTouchHelper(
        deckItemTouchHelperCallback: DeckItemTouchHelperCallback
    ): ItemTouchHelper {
        return ItemTouchHelper(deckItemTouchHelperCallback)
    }

    @Singleton
    @Provides
    fun provideAddCardsAdapter(
        deckObservable: DeckObservable,
        repository: DeckRepository
    ): AddCardsAdapter {
        return AddCardsAdapter(deckObservable, repository)
    }
}
