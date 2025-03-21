package org.fansin.intellideck.deck.domain

class DeckObservable {

    private val observers = mutableListOf<DeckObserver>()

    fun addObserver(observer: DeckObserver) {
        observers.add(observer)
    }

    fun onItemAdded(item: DeckItem, position: Int) {
        observers.forEach { it.onItemAdded(item, position) }
    }

    fun onItemRemoved(item: DeckItem, position: Int) {
        observers.forEach { it.onItemRemoved(item, position) }
    }

    fun onItemMoved(from: Int, to: Int) {
        observers.forEach { it.onItemMoved(from, to) }
    }

    fun onItemsReceived() {
        observers.forEach { it.onDataReceived() }
    }

    fun onEnterEditMode() {
        observers.forEach { it.onEnterEditMode() }
    }

    fun onExitEditMode() {
        observers.forEach { it.onExitEditMode() }
    }
}
