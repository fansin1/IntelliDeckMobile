package org.fansin.intellideck.deck.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_cards.*
import org.fansin.intellideck.App
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.domain.DeckObservable
import javax.inject.Inject

class CardsFragment : Fragment() {

    @Inject
    lateinit var deckAdapter: DeckAdapter

    @Inject
    lateinit var appConfig: AppConfig

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    @Inject
    lateinit var deckObservable: DeckObservable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.applicationComponent.inject(this)
        configureDeck()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun configureDeck() {
        activeCardsRecyclerView.layoutManager = GridLayoutManager(context, appConfig.spanCount)
        activeCardsRecyclerView.recycledViewPool.setMaxRecycledViews(0, Int.MAX_VALUE)
        activeCardsRecyclerView.adapter = deckAdapter
        itemTouchHelper.attachToRecyclerView(activeCardsRecyclerView)
        emptySpace.setOnClickListener {
            deckObservable.onExitEditMode()
        }
    }
}
