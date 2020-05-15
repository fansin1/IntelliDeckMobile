package org.fansin.intellideck.deck

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import org.fansin.intellideck.R

class DeckView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val cardImage: ImageView
    private val cardView: CardView
    private val closeButton: ImageButton

    init {
        inflate(context, R.layout.deck_view, this)
        cardView = findViewById(R.id.cardView)
        closeButton = findViewById(R.id.closeButton)
        cardImage = cardView.findViewById(R.id.cardImage)
    }

    fun enterEditMode() {
        closeButton.visibility = View.VISIBLE
        cardImage.setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY)
    }

    fun exitEditMode() {
        closeButton.visibility = View.GONE
        cardImage.clearColorFilter()
    }

    fun setDrawable(drawable: Drawable) {
        cardImage.setImageDrawable(drawable)
    }

    fun setCardClickListener(clickListener: OnClickListener) {
        cardView.setOnClickListener(clickListener)
    }

    fun setCloseClickListener(clickListener: OnClickListener) {
        closeButton.setOnClickListener(clickListener)
    }
}
