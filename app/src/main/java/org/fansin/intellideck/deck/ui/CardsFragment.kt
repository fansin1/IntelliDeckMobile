package org.fansin.intellideck.deck.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_cards.*
import org.fansin.intellideck.App
import org.fansin.intellideck.AppConfig
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.domain.ConnectionObservable
import org.fansin.intellideck.deck.domain.ConnectionObserver
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.network.DeckClient
import org.fansin.intellideck.deck.network.SocketParams
import javax.inject.Inject

class CardsFragment : Fragment(), ConnectionObserver {

    @Inject
    lateinit var deckAdapter: DeckAdapter

    @Inject
    lateinit var deckClient: DeckClient

    @Inject
    lateinit var addCardsAdapter: AddCardsAdapter

    @Inject
    lateinit var appConfig: AppConfig

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    @Inject
    lateinit var deckObservable: DeckObservable

    @Inject
    lateinit var connectionObservable: ConnectionObservable

    @Inject
    lateinit var connectionDialog: ConnectionDialog

    private lateinit var connectionItem: MenuItem
    private val navController by lazy {
        findNavController()
    }
    private var isConnected = false

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
        connectionObservable.addObserver(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        connectionItem = menu.findItem(R.id.action_connect)
        updateTitle()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                navController.navigate(R.id.AddCardsFragment)
                addCardsAdapter.notifyDataSetChanged()
                addCardsAdapter.setMode(false)
                true
            }
            R.id.action_connect -> {
                if (isConnected) {
                    deckClient.disconnect()
                } else {
                    connectionDialog.showDialog(this) { ip ->
                        deckClient.connect(SocketParams(ip, 3333, 5000))
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onConnected() {
        isConnected = true
        updateTitle()
    }

    override fun onDisconnected() {
        isConnected = false
        updateTitle()
    }

    private fun updateTitle() {
        if (isConnected) {
            connectionItem.title = "Disconnect"
        } else {
            connectionItem.title = "Connect"
        }
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
