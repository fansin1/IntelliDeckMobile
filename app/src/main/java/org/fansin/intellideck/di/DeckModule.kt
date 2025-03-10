package org.fansin.intellideck.di

import android.content.Context
import android.os.Vibrator
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.MainActivity
import org.fansin.intellideck.Persistence
import org.fansin.intellideck.deck.domain.ConnectionObservable
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckRepository
import org.fansin.intellideck.deck.network.DeckClient
import org.fansin.intellideck.deck.ui.*
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
        onDeckClicksListenerFactory: OnDeckClicksListenerFactory,
        deckRepository: DeckRepository,
        deckObservable: DeckObservable
    ): DeckAdapter {
        return DeckAdapter(
            deckObservable,
            onDeckClicksListenerFactory,
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
        repository: DeckRepository,
        addingDialog: AddingDialog
    ): AddCardsAdapter {
        return AddCardsAdapter(deckObservable, repository, addingDialog)
    }

    @Singleton
    @Provides
    fun provideDeckClient(
        deckRepository: DeckRepository,
        connectionObservable: ConnectionObservable
    ): DeckClient {
        return DeckClient(deckRepository, connectionObservable)
    }

    @Singleton
    @Provides
    fun provideOnDeckClicksListenerFactory(
        deckClient: DeckClient,
        deckObservable: DeckObservable
    ): OnDeckClicksListenerFactory {
        return OnDeckClicksListenerFactory(deckClient, deckObservable)
    }

    @Singleton
    @Provides
    fun provideConnectionDialog(): ConnectionDialog {
        return ConnectionDialog()
    }

    @Singleton
    @Provides
    fun provideAddingDialog(activity: MainActivity): AddingDialog {
        return AddingDialog(activity)
    }

    @Provides
    @Singleton
    fun providePersistence(
        gson: Gson,
        context: Context,
        deckRepository: DeckRepository
    ): Persistence {
        return Persistence(context, deckRepository, gson)
    }
}
