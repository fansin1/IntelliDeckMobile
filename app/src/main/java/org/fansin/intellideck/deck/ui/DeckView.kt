package org.fansin.intellideck.deck.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.domain.DeckItem

class DeckView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val cardImage: ImageView
    private val cardView: CardView
    private val cardTextView: TextView
    private val closeButton: ImageButton

    init {
        inflate(context, R.layout.deck_view, this)
        cardView = findViewById(R.id.cardView)
        closeButton = findViewById(R.id.closeButton)
        cardImage = cardView.findViewById(R.id.cardImage)
        cardTextView = findViewById(R.id.cardTextView)
    }

    fun enterEditMode() {
        cardView.isEnabled = false
        closeButton.visibility = View.VISIBLE
        cardImage.setColorFilter(
            Color.rgb(123, 123, 123),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
    }

    fun exitEditMode() {
        cardView.isEnabled = true
        closeButton.visibility = View.GONE
        cardImage.clearColorFilter()
    }

    fun setData(deckItem: DeckItem) {
        Glide.with(this).load(deckItem.drawable).into(cardImage)
        cardTextView.text = deckItem.command.name
    }

    fun setDeckClicksListener(deckClicksListener: OnDeckClicksListener) {
        cardView.setOnClickListener { deckClicksListener.onCardClickListener() }
        cardView.setOnLongClickListener { deckClicksListener.onCardLongClickListener() }
        closeButton.setOnClickListener { deckClicksListener.onCloseClickListener() }
    }
}
