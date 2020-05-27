package org.fansin.intellideck

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.fansin.intellideck.deck.network.DeckClient
import org.fansin.intellideck.deck.network.SocketParams
import org.fansin.intellideck.deck.ui.AddCardsAdapter
import org.fansin.intellideck.deck.ui.ConnectionDialogFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var addCardsAdapter: AddCardsAdapter

    @Inject
    lateinit var deckClient: DeckClient

    @Inject
    lateinit var connectionDialogFactory: ConnectionDialogFactory

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        App.applicationComponent.inject(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                navController.navigate(R.id.AddCardsFragment)
                addCardsAdapter.notifyDataSetChanged()
                true
            }
            R.id.action_connect -> {
                connectionDialogFactory.showDialog(this) { ip ->
                    deckClient.connect(SocketParams(ip, 3333, 5000))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
