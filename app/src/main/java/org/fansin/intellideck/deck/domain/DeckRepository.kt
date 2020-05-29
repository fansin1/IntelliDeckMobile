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
            val isDebug: Boolean
            val realName: String
            when {
                item.startsWith("Run-") -> {
                    isDebug = false
                    realName = item.replace("Run-", "")
                }
                item.startsWith("Debug-") -> {
                    isDebug = true
                    realName = item.replace("Debug-", "")
                }
                else -> {
                    return
                }
            }

            receivedItems.add(createDeckItem(realName, isDebug))
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
