package org.fansin.intellideck.deck.ui.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_active_cards.view.*
import org.fansin.intellideck.App
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.ui.GridSpacingItemDecoration
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

        view.activeCardsRecyclerView.layoutManager = GridLayoutManager(context, appConfig.spanCount)
        view.activeCardsRecyclerView.recycledViewPool.setMaxRecycledViews(0, Int.MAX_VALUE)
        val itemDecoration =
            GridSpacingItemDecoration(
                appConfig.spanCount,
                appConfig.spacing,
                appConfig.includeEdge,
                appConfig.headerNum
            )
        view.activeCardsRecyclerView.addItemDecoration(itemDecoration)
        view.activeCardsRecyclerView.adapter = activeDeckAdapter
        itemTouchHelper.attachToRecyclerView(view.activeCardsRecyclerView)
        view.emptySpace.setOnClickListener {
            activeDeckAdapter.exitEditMode()
        }
    }
}
