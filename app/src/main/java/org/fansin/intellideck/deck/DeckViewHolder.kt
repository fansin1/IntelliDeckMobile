package org.fansin.intellideck.deck

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.deck.DeckItem
import org.fansin.intellideck.deck.DeckView

class DeckViewHolder(private val view: DeckView) : RecyclerView.ViewHolder(view) {

    fun bind(deckItem: DeckItem) {
        view.setDrawable(deckItem.drawable)
    }

    fun setCardClickListener(clickListener: View.OnClickListener) {
        view.setCardClickListener(clickListener)
    }

    fun setCloseClickListener(clickListener: View.OnClickListener) {
        view.setCloseClickListener(clickListener)
    }

}
