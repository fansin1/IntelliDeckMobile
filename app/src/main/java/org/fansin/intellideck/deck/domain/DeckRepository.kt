package org.fansin.intellideck.deck.domain

import android.content.Context
import org.fansin.intellideck.R

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

    // TODO: clean that method
    fun parseCommands(commands: String) {
        val receivedItems = mutableSetOf<DeckItem>()
        val items = commands.split("\n").dropLastWhile { it.isBlank() }
        val count = items.first().drop(6).toInt()
        for (i in 1..count) {
            val item = items[i]
            val realName = if (item.startsWith("Run-")) {
                item.substring(4)
            } else {
                item
            }

            receivedItems.add(createDeckItem(realName, true))
            receivedItems.add(createDeckItem(realName, false))
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

    private fun createDeckItem(name: String, isDebug: Boolean): DeckItem {
        val drawable =
            if (isDebug) {
                context.getDrawable(R.drawable.ic_debug)!!
            } else {
                context.getDrawable(R.drawable.ic_execute)!!
            }
        return DeckItem(DeckCommand(name, isDebug), drawable)
    }
}
