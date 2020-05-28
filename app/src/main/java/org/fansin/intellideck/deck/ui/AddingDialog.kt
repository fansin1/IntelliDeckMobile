package org.fansin.intellideck.deck.ui

import androidx.appcompat.app.AppCompatActivity
import petrov.kristiyan.colorpicker.ColorPicker

class AddingDialog(
    private val activity: AppCompatActivity
) {

    private var clicked = false

    fun showDialog(colorPicked: (color: Int) -> Unit) {
        clicked = false

        val colorPicker = ColorPicker(activity)
        colorPicker.setOnChooseColorListener(chooseColorListener(colorPicked))
        colorPicker.show()
    }

    private fun chooseColorListener(colorPicked: (color: Int) -> Unit)
            = object : ColorPicker.OnChooseColorListener {

        override fun onChooseColor(position: Int, color: Int) {
            if (clicked) {
                return
            }
            clicked = true
            colorPicked(color)
        }

        override fun onCancel() {
            // just cancel
        }
    }
}
