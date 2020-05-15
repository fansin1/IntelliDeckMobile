package org.fansin.intellideck.deck.active

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_active_cards.view.*
import org.fansin.intellideck.*
import org.fansin.intellideck.deck.GridSpacingItemDecoration
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ActiveCardsFragment : Fragment() {

    @Inject
    lateinit var activeDeckAdapter: ActiveDeckAdapter

    @Inject
    lateinit var appConfig: AppConfig

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.applicationComponent.inject(this)

        view.activeCardsRecyclerView.layoutManager = GridLayoutManager(context, appConfig.spanCount)
        val itemDecoration =
            GridSpacingItemDecoration(
                appConfig.spanCount,
                appConfig.spacing,
                appConfig.includeEdge,
                appConfig.headerNum
            )
        view.activeCardsRecyclerView.addItemDecoration(itemDecoration)
        view.activeCardsRecyclerView.adapter = activeDeckAdapter
    }
}
