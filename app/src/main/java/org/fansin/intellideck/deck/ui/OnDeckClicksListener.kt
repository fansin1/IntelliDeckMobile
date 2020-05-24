package org.fansin.intellideck.deck.ui

interface OnDeckClicksListener {

    fun onCloseClickListener()
    fun onCardClickListener()
    fun onCardLongClickListener(): Boolean
}