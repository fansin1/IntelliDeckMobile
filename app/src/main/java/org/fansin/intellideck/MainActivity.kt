package org.fansin.intellideck

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.fansin.intellideck.deck.domain.DeckObservable
import org.fansin.intellideck.deck.domain.DeckRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var deckRepository: DeckRepository

    @Inject
    lateinit var deckObservable: DeckObservable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        App.applicationComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //TODO: Create toolbar controller
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add -> {
                deckObservable.onItemAdded(
                    deckRepository.inactiveItems.first(),
                    deckRepository.activeItems.size)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
