package org.fansin.intellideck.deck.domain

import android.content.Context
import android.widget.Toast

class ConnectionObservable(
    private val context: Context
) {

    private val observers = mutableListOf<ConnectionObserver>()

    fun addObserver(observer: ConnectionObserver) {
        observers.add(observer)
    }

    fun onConnected() {
        observers.forEach {
            it.onConnected()
        }
        Toast.makeText(context, "CONNECTED", Toast.LENGTH_SHORT).show()
    }

    fun onDisconnected() {
        observers.forEach {
            it.onDisconnected()
        }
        Toast.makeText(context, "DISCONNECTED", Toast.LENGTH_SHORT).show()
    }
}
