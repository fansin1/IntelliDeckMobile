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
import androidx.core.graphics.ColorUtils
import com.bumptech.glide.Glide
import org.fansin.intellideck.R
import org.fansin.intellideck.deck.domain.DeckItem

private val defaultCardBackground = Color.parseColor("#EAEAEA")
private const val editModeCardBackground = Color.GRAY

class DeckView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val cardImageView: ImageView
    private val cardView: CardView
    private val cardTextView: TextView
    private val closeButton: ImageButton
    private var tintColor = 0

    init {
        inflate(context, R.layout.deck_view, this)
        cardView = findViewById(R.id.cardView)
        closeButton = findViewById(R.id.closeButton)
        cardImageView = cardView.findViewById(R.id.cardImage)
        cardTextView = findViewById(R.id.cardTextView)
    }

    fun enterEditMode() {
        cardView.isEnabled = false
        cardView.setCardBackgroundColor(editModeCardBackground)
        closeButton.visibility = View.VISIBLE

        val color = ColorUtils.blendARGB(Color.rgb(0, 0, 0), tintColor, 0.5f)
        cardImageView.setColorFilter(color)
    }

    fun exitEditMode() {
        cardView.isEnabled = true
        cardView.setCardBackgroundColor(defaultCardBackground)
        closeButton.visibility = View.GONE
        cardImageView.setColorFilter(tintColor)
    }

    fun setData(deckItem: DeckItem) {
        Glide.with(this).load(deckItem.drawable).into(cardImageView)
        tintColor = deckItem.color
        cardImageView.setColorFilter(tintColor)
        cardTextView.text = deckItem.command.name
    }

    fun setDeckClicksListener(deckClicksListener: OnDeckClicksListener) {
        cardView.setOnClickListener { deckClicksListener.onCardClickListener() }
        cardView.setOnLongClickListener { deckClicksListener.onCardLongClickListener() }
        closeButton.setOnClickListener { deckClicksListener.onCloseClickListener() }
    }
}
