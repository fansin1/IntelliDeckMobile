package org.fansin.intellideck.deck.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckObserver
import org.fansin.intellideck.deck.domain.DeckRepository

class AddCardsAdapter(
    private val deckObservable: DeckObservable,
    private val repository: DeckRepository
) : RecyclerView.Adapter<AddCardViewHolder>(), DeckObserver {

    private var realItems = filterItems(false)
    private var debugMode = false

    init {
        deckObservable.addObserver(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCardViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_card_item_view, parent, false)

        return AddCardViewHolder(view)
    }

    override fun getItemCount() = realItems.size

    override fun onBindViewHolder(holder: AddCardViewHolder, position: Int) {
        val item = realItems[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            deckObservable.onItemAdded(item, repository.activeItems.size)
        }
    }

    override fun onItemAdded(item: DeckItem, position: Int) {
        val pos = realItems.indexOf(item)
        realItems.removeAt(pos)
        notifyItemRemoved(pos)
    }

    override fun onItemRemoved(item: DeckItem, position: Int) {
        realItems.add(item)
        notifyItemInserted(realItems.size - 1)
    }

    override fun onDataReceived() {
        notifyDataSetChanged()
        realItems = filterItems(debugMode)
    }

    fun setMode(isDebug: Boolean) {
        debugMode = isDebug
        realItems = filterItems(isDebug)
        notifyDataSetChanged()
    }

    private fun filterItems(isDebug: Boolean): MutableList<DeckItem> {
        return repository.inactiveItems.filter { deckItem ->
            deckItem.command.isDebug == isDebug
        }.toMutableList()
    }
}
