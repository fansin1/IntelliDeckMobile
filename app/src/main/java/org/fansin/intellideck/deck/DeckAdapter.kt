package org.fansin.intellideck.deck

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DeckAdapter(
    protected val repository: DeckRepository
): RecyclerView.Adapter<DeckViewHolder>() {

    abstract val items: List<DeckItem>

    init {
        repository.addItemsChangedListener {
            notifyDataSetChanged()
        }
    }

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