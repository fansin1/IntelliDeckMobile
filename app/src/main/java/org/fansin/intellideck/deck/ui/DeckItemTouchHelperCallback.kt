package org.fansin.intellideck.deck.ui

import android.os.Vibrator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.deck.domain.DeckObservable

class DeckItemTouchHelperCallback(
    private val appConfig: AppConfig,
    private val vibrator: Vibrator,
    private val deckObservable: DeckObservable
) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // do nothing
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or
                ItemTouchHelper.DOWN or
                ItemTouchHelper.LEFT or
                ItemTouchHelper.RIGHT
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (source.itemViewType != target.itemViewType) {
            return false
        }

        deckObservable.onItemMoved(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            vibrator.vibrate(appConfig.onClickVibrationDuration)
        }
    }
}
