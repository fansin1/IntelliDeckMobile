package org.fansin.intellideck.deck.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckObserver

class DeckAdapter(
    deckObservable: DeckObservable,
    private val onDeckClicksListenerFactory: OnDeckClicksListenerFactory,
    private val items: List<DeckItem>
) : RecyclerView.Adapter<DeckViewHolder>(), DeckObserver {

    private var isInEditMode = false

    init {
        deckObservable.addObserver(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val deckView = DeckView(parent.context)

        return DeckViewHolder(deckView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        updateMode(holder.view)
        holder.view.setDeckClicksListener(onDeckClicksListenerFactory.createListener(items, item))
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

    override fun onDataReceived() {
        notifyDataSetChanged()
    }

    override fun onEnterEditMode() {
        isInEditMode = true
        notifyDataSetChanged()
    }

    override fun onExitEditMode() {
        isInEditMode = false
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
