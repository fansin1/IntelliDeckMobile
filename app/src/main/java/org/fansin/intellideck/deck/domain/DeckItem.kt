package org.fansin.intellideck.deck.domain

import android.graphics.Color

data class DeckItem(val command: DeckCommand, var color: Int = Color.BLACK) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is DeckItem) {
            return false
        }
        return command == other.command
    }

    override fun hashCode(): Int {
        return command.hashCode()
    }
}
