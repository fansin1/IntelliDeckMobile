package org.fansin.intellideck.deck.domain

import android.content.Context

class DeckRepository(
    context: Context,
    deckObservable: DeckObservable
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

        override fun onDataReceived(items: MutableList<DeckItem>) {
            // do nothing
        }
    }

    val activeItems = mutableListOf(
        DeckItem(
            "Test1",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test2",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test3",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test4",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test5",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test6",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test7",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test8",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        )
    )
    val inactiveItems = mutableListOf<DeckItem>(
        DeckItem(
            "Test9",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test10",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test11",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test12",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test13",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        )
    )

    init {
        deckObservable.addObserver(deckObserver)
    }
}
