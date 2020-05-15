package org.fansin.intellideck.deck.inactive

import org.fansin.intellideck.deck.DeckAdapter
import org.fansin.intellideck.deck.DeckItem
import org.fansin.intellideck.deck.DeckRepository
import org.fansin.intellideck.deck.DeckViewHolder

class InactiveDeckAdapter(repository: DeckRepository) : DeckAdapter(repository) {
    override val items: List<DeckItem>
        get() = repository.inactiveItems

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }
}
