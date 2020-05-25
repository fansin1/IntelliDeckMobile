package org.fansin.intellideck.deck.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_card_item_view.view.*
import org.fansin.intellideck.deck.domain.DeckItem

class AddCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var deckItem: DeckItem

    fun bind(deckItem: DeckItem) {
        this.deckItem = deckItem
        itemView.title.text = deckItem.name
    }
}
