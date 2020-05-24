package org.fansin.intellideck.deck.domain

interface DeckObserver {
    fun onItemAdded(item: DeckItem, position: Int)
    fun onItemRemoved(item: DeckItem, position: Int)
    fun onItemMoved(from: Int, to: Int)
    fun onDataReceived(items: MutableList<DeckItem>)
}