package org.fansin.intellideck.deck.ui

import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.deck.domain.DeckItem

class DeckViewHolder(view: DeckView) : RecyclerView.ViewHolder(view) {

    val view: DeckView
        get() = itemView as DeckView

    fun bind(deckItem: DeckItem) {
        view.setData(deckItem)
    }
}
