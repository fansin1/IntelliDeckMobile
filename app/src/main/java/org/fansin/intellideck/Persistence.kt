package org.fansin.intellideck

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import org.fansin.intellideck.deck.domain.DeckItem
import org.fansin.intellideck.deck.domain.DeckRepository

private const val PERSISTENCE = "persistence"
private const val ACTIVE_ITEMS = "active_items"
private const val INACTIVE_ITEMS = "inactive_items"

class Persistence(
    context: Context,
    private val deckRepository: DeckRepository,
    private val gson: Gson
) {

    private val preferences = context.getSharedPreferences(PERSISTENCE, Context.MODE_PRIVATE)

    fun saveDeck() {
        preferences.edit {
            putString(ACTIVE_ITEMS, gson.toJson(deckRepository.activeItems))
            putString(INACTIVE_ITEMS, gson.toJson(deckRepository.inactiveItems))
        }
    }

    fun loadDeck() {
        deckRepository.activeItems.clear()
        deckRepository.inactiveItems.clear()
        deckRepository.activeItems.addAll(getItems(ACTIVE_ITEMS))
        deckRepository.inactiveItems.addAll(getItems(INACTIVE_ITEMS))
    }

    @Suppress("UNCHECKED_CAST")
    private fun getItems(key: String): List<DeckItem> {
        val jsonItems = preferences.getString(key, "")
        return (gson.fromJson(jsonItems, Array<DeckItem>::class.java) ?: arrayOf()).toList()
    }
}
