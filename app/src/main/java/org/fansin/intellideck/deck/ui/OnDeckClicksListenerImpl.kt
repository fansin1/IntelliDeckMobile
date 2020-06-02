package org.fansin.intellideck.deck.ui

import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.network.DeckClient

class OnDeckClicksListenerImpl(
    private val deckClient: DeckClient,
    private val deckObservable: DeckObservable,
    private val deckItem: DeckItem,
    private val deckItemPosition: () -> Int
) : OnDeckClicksListener {

    override fun onCloseClickListener() {
        deckObservable.onItemRemoved(deckItem, deckItemPosition())
    }

    override fun onCardClickListener() {
        if (deckClient.isConnected) {
            deckClient.sendCommand(deckItem.command)
        }
    }

    override fun onCardLongClickListener(): Boolean {
        deckObservable.onEnterEditMode()
        return true
    }
}
