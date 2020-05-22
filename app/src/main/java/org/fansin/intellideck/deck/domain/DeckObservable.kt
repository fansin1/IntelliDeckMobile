package org.fansin.intellideck.deck.domain

class DeckObservable {

    private val observers = mutableListOf<DeckObserver>()

    fun addObserver(observer: DeckObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: DeckObserver) {
        observers.remove(observer)
    }

    fun onItemAdded(item: DeckItem) {
        observers.forEach { it.onItemAdded(item) }
    }

    fun onItemRemoved(item: DeckItem) {
        observers.forEach { it.onItemRemoved(item) }
    }

    fun onItemsReceived(items: MutableList<DeckItem>) {
        observers.forEach { it.onDataReceived(items) }
    }
}
