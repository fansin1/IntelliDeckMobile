package org.fansin.intellideck.deck.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_add_cards.*
import org.fansin.intellideck.App
import org.fansin.intellideck.R
import javax.inject.Inject

class AddCardsFragment : Fragment() {

    @Inject
    lateinit var addCardsAdapter: AddCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.applicationComponent.inject(this)
        addCardsRecyclerView.layoutManager = LinearLayoutManager(context)
        addCardsRecyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        addCardsRecyclerView.adapter = addCardsAdapter
        materialButtonToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) {
                return@addOnButtonCheckedListener
            }
            when(checkedId) {
                R.id.runButton -> {
                    addCardsAdapter.setMode(false)
                }
                else -> addCardsAdapter.setMode(true)
            }
        }
    }
}
