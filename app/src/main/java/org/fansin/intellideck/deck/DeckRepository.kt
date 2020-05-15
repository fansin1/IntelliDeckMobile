package org.fansin.intellideck.deck

import android.R
import android.content.Context

typealias ItemsChangedListener = () -> Unit

class DeckRepository(context: Context) {

    private val listeners = mutableListOf<ItemsChangedListener>()
    val activeItems = mutableListOf(
        DeckItem(
            0,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            1,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            2,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            3,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            4,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            5,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            6,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        ),
        DeckItem(
            7,
            context.getDrawable(R.drawable.sym_def_app_icon)!!
        )
    )
    val inactiveItems = mutableListOf<DeckItem>()

    fun makeItemInactive(deckItem: DeckItem) {
        activeItems.remove(deckItem)
        inactiveItems.add(deckItem)
        notifyItemsChanged()
    }

    fun makeItemActive(deckItem: DeckItem) {
        inactiveItems.remove(deckItem)
        activeItems.add(deckItem)
        notifyItemsChanged()
    }

    fun addItemsChangedListener(itemsChangedListener: ItemsChangedListener) {
        listeners.add(itemsChangedListener)
    }

    private fun notifyItemsChanged() {
        for (listener in listeners) {
            listener()
        }
    }
}
