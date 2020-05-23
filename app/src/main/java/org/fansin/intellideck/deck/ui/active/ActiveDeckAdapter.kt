package org.fansin.intellideck.deck.ui.active

import android.view.View
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckObserver
import org.fansin.intellideck.deck.ui.DeckAdapter
import org.fansin.intellideck.deck.ui.DeckView
import org.fansin.intellideck.deck.ui.DeckViewHolder

class ActiveDeckAdapter(
    private val deckObservable: DeckObservable,
    items: MutableList<DeckItem>
) : DeckAdapter(items) {

    private var isInEditMode = false

    private val deckObserver = object : DeckObserver {
        override fun onItemAdded(item: DeckItem, position: Int) {
            notifyItemInserted(position)
        }

        override fun onItemRemoved(item: DeckItem, position: Int) {
            notifyItemRemoved(position)
        }

        override fun onDataReceived(items: MutableList<DeckItem>) {
            updateItems(items)
        }
    }

    init {
        deckObservable.addObserver(deckObserver)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder.itemView !is DeckView) {
            return
        }

        updateMode(holder.itemView)
        val item = items[position]
        holder.view.setCloseClickListener(View.OnClickListener {
            deckObservable.onItemRemoved(item, items.indexOf(item))
        })
        holder.view.setOnLongClickListener {
            enterEditMode()
            true
        }
    }

    fun enterEditMode() {
        isInEditMode = true
        notifyDataSetChanged()
    }

    fun exitEditMode() {
        isInEditMode = false
        notifyDataSetChanged()
    }

    private fun updateItems(newItems: MutableList<DeckItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun updateMode(deckView: DeckView) {
        if (isInEditMode) {
            deckView.enterEditMode()
        } else {
            deckView.exitEditMode()
        }
    }
}
