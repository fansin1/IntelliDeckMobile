package org.fansin.intellideck.deck.ui.inactive

import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.ui.DeckAdapter
import org.fansin.intellideck.deck.ui.DeckViewHolder

class InactiveDeckAdapter(
    private val deckObservable: DeckObservable,
    items: MutableList<DeckItem>
) : DeckAdapter(items) {

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }
}
