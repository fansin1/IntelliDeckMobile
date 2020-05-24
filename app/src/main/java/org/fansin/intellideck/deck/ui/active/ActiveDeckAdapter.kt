package org.fansin.intellideck.deck.ui.active

import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckObserver
import org.fansin.intellideck.deck.ui.DeckAdapter
import org.fansin.intellideck.deck.ui.DeckView
import org.fansin.intellideck.deck.ui.DeckViewHolder

class ActiveDeckAdapter(
    private val deckObservable: DeckObservable,
    items: MutableList<DeckItem>
) : DeckAdapter(items), DeckObserver {

    private var isInEditMode = false

    init {
        deckObservable.addObserver(this)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        updateMode(holder.view)
        val item = items[position]
        holder.view.setDeckClicksListener(
            ActiveOnDeckClicksListener(
                deckObservable, item
            ) { items.indexOf(item) }
        )
    }

    override fun onItemAdded(item: DeckItem, position: Int) {
        notifyItemInserted(position)
    }

    override fun onItemRemoved(item: DeckItem, position: Int) {
        notifyItemRemoved(position)
    }

    override fun onItemMoved(from: Int, to: Int) {
        notifyItemMoved(from, to)
    }

    override fun onDataReceived(items: MutableList<DeckItem>) {
        updateItems(items)
    }

    override fun onEnterEditMode() {
        isInEditMode = true
        notifyDataSetChanged()
    }

    override fun onExitEditMode() {
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
