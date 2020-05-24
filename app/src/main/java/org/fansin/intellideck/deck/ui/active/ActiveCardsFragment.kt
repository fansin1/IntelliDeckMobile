package org.fansin.intellideck.deck.ui.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_active_cards.*
import org.fansin.intellideck.App
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.R
import javax.inject.Inject

class ActiveCardsFragment : Fragment() {

    @Inject
    lateinit var activeDeckAdapter: ActiveDeckAdapter

    @Inject
    lateinit var appConfig: AppConfig

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.applicationComponent.inject(this)
        configureDeck()
    }

    private fun configureDeck() {
        activeCardsRecyclerView.layoutManager = GridLayoutManager(context, appConfig.spanCount)
        activeCardsRecyclerView.recycledViewPool.setMaxRecycledViews(0, Int.MAX_VALUE)
        activeCardsRecyclerView.adapter = activeDeckAdapter
        itemTouchHelper.attachToRecyclerView(activeCardsRecyclerView)
        emptySpace.setOnClickListener {
            activeDeckAdapter.exitEditMode()
        }
    }
}
