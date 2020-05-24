package org.fansin.intellideck.deck.ui.active

import android.util.Log
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.ui.OnDeckClicksListener

class ActiveOnDeckClicksListener(
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
