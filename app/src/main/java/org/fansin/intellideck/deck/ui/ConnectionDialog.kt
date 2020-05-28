package org.fansin.intellideck.deck.ui

import android.app.Activity
import android.app.AlertDialog
import kotlinx.android.synthetic.main.connection_dialog.view.*
import org.fansin.intellideck.R


class ConnectionDialog {

    fun showDialog(activity: Activity, connectionClickListener: (String) -> Unit) {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.connection_dialog, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        view.cancelButton.setOnClickListener {
            dialog.cancel()
        }
        view.connectButton.setOnClickListener {
            connectionClickListener(view.ipAddressEditText.text.toString())
            dialog.cancel()
        }
    }
}
