package org.fansin.intellideck

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.fansin.intellideck.deck.network.DeckClient
import org.fansin.intellideck.deck.network.SocketParams
import org.fansin.intellideck.deck.ui.AddCardsAdapter
import org.fansin.intellideck.deck.ui.ConnectionDialog
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var addCardsAdapter: AddCardsAdapter

    @Inject
    lateinit var deckClient: DeckClient

    @Inject
    lateinit var connectionDialog: ConnectionDialog

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
        (application as App).onMainActivityCreated(this)
        App.applicationComponent.inject(this)
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
                connectionDialog.showDialog(this) { ip ->
                    deckClient.connect(SocketParams(ip, 3333, 5000))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
