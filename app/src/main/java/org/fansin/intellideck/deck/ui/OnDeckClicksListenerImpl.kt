package org.fansin.intellideck.deck.ui

import android.util.Log
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable

class OnDeckClicksListenerImpl(
    private val deckObservable: DeckObservable,
    private val deckItem: DeckItem,
    private val deckItemPosition: () -> Int
) : OnDeckClicksListener {

    override fun onCloseClickListener() {
        deckObservable.onItemRemoved(deckItem, deckItemPosition())
    }

    override fun onCardClickListener() {
        Log.d("Test", deckItem.name)
    }

    override fun onCardLongClickListener(): Boolean {
        deckObservable.onEnterEditMode()
        return true
    }
}
