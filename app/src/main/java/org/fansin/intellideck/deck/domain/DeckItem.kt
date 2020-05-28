package org.fansin.intellideck.deck.domain

import android.graphics.Color
import android.graphics.drawable.Drawable

data class DeckItem(val command: DeckCommand, val drawable: Drawable, var color: Int = Color.BLACK)
