package org.fansin.intellideck.deck.active

import android.view.View
import org.fansin.intellideck.deck.*

class ActiveDeckAdapter(repository: DeckRepository) : DeckAdapter(repository) {

    private var isInEditMode = false

    override val items: List<DeckItem>
        get() = repository.activeItems

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder.itemView is DeckView) {
            updateMode(holder.itemView)
        }
        holder.setCloseClickListener(View.OnClickListener {
            repository.makeItemInactive(items[position])
        })
    }

    fun enterEditMode() {
        isInEditMode = true
        notifyDataSetChanged()
    }

    fun exitEditMode() {
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
