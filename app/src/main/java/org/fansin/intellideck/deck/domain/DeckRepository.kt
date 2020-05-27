package org.fansin.intellideck.deck.domain

import android.content.Context

class DeckRepository(
    private val context: Context,
    private val deckObservable: DeckObservable
) {

    private val deckObserver = object : DeckObserver {
        override fun onItemAdded(item: DeckItem, position: Int) {
            inactiveItems.remove(item)
            activeItems.add(item)
        }

        override fun onItemRemoved(item: DeckItem, position: Int) {
            activeItems.remove(item)
            inactiveItems.add(item)
        }

        override fun onItemMoved(from: Int, to: Int) {
            val item = activeItems[from]
            activeItems.removeAt(from)
            activeItems.add(to, item)
        }
    }

    val activeItems = mutableListOf<DeckItem>()
    val inactiveItems = mutableListOf<DeckItem>()

    init {
        deckObservable.addObserver(deckObserver)
    }

    fun parseCommands(items: String) {
        val receivedItems = mutableSetOf<DeckItem>()
        for (item in items.split(" ").dropLastWhile { it.isBlank() }) {
            val realName = if (item.startsWith("Run-")) {
                item.substring(4)
            } else {
                item
            }

            receivedItems.add(
                DeckItem(
                    DeckCommand(realName),
                    context.getDrawable(android.R.drawable.sym_def_app_icon)!!
                )
            )
        }

        val newActiveItems = activeItems.filter { it in receivedItems }
        val newInactiveItems = inactiveItems.filter { it in receivedItems }
        val newItems = receivedItems.filter { it !in newActiveItems && it !in newInactiveItems }
        activeItems.clear()
        inactiveItems.clear()
        activeItems.addAll(newActiveItems)
        inactiveItems.addAll(newInactiveItems)
        inactiveItems.addAll(newItems)
        deckObservable.onItemsReceived()
    }
}
