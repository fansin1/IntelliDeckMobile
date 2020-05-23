package org.fansin.intellideck.deck.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.deck.domain.DeckItem

open class DeckAdapter(
    protected val items: MutableList<DeckItem>
) : RecyclerView.Adapter<DeckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val deckView = DeckView(parent.context)

        return DeckViewHolder(deckView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}
