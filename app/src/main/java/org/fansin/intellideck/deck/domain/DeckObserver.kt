package org.fansin.intellideck.deck.domain

interface DeckObserver {
    fun onItemAdded(item: DeckItem)
    fun onItemRemoved(item: DeckItem)
    fun onDataReceived(items: MutableList<DeckItem>)
}