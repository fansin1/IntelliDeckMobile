package org.fansin.intellideck.deck.ui

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.connection_dialog.view.*
import org.fansin.intellideck.R


class ConnectionDialog {

    private var lastIp = ""

    fun showDialog(fragment: Fragment, connectionClickListener: (String) -> Unit) {
        val builder = AlertDialog.Builder(fragment.context)
        val inflater = fragment.layoutInflater
        val view = inflater.inflate(R.layout.connection_dialog, null)
        view.ipAddressEditText.setText(lastIp)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        view.cancelButton.setOnClickListener {
            dialog.cancel()
        }
        view.connectButton.setOnClickListener {
            lastIp = view.ipAddressEditText.text.toString()
            connectionClickListener(lastIp)
            dialog.cancel()
        }
    }
}
