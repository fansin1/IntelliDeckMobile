package org.fansin.intellideck.deck.domain

interface DeckObserver {
    @JvmDefault
    fun onItemAdded(item: DeckItem, position: Int) {
        // to override
    }
    @JvmDefault
    fun onItemRemoved(item: DeckItem, position: Int) {
        // to override
    }
    @JvmDefault
    fun onItemMoved(from: Int, to: Int) {
        // to override
    }
    @JvmDefault
    fun onDataReceived(items: MutableList<DeckItem>) {
        // to override
    }
    @JvmDefault
    fun onEnterEditMode() {
        // to override
    }
    @JvmDefault
    fun onExitEditMode() {
        // to override
    }
}
