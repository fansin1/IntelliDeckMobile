package org.fansin.intellideck.deck.domain

interface ConnectionObserver {
    fun onConnected()
    fun onDisconnected()
}
