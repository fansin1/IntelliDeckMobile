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

        override fun onDataReceived(items: MutableList<DeckItem>) {
            // do nothing
        }
    }

    val activeItems = mutableListOf(
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        )
    )
    val inactiveItems = mutableListOf<DeckItem>(
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            "Test",
            context.getDrawable(android.R.drawable.sym_def_app_icon)!!
        )
    )

    init {
        deckObservable.addObserver(deckObserver)
    }
}
