package org.fansin.intellideck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var persistence: Persistence

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

    override fun onPause() {
        super.onPause()
        persistence.saveDeck()
    }

    override fun onStart() {
        super.onStart()
        persistence.loadDeck()
    }
}
