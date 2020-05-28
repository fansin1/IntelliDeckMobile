package org.fansin.intellideck.deck.ui

import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.network.DeckClient

class OnDeckClicksListenerFactory(
    private val deckClient: DeckClient,
    private val deckObservable: DeckObservable
) {
    fun createListener(items: List<DeckItem>, item: DeckItem) =
        OnDeckClicksListenerImpl(deckClient, deckObservable, item) { items.indexOf(item) }
}
