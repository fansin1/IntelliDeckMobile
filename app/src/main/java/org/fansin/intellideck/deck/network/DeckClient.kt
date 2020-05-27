package org.fansin.intellideck.deck.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fansin.intellideck.deck.domain.DeckCommand
import org.fansin.intellideck.deck.domain.DeckRepository
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException

class DeckClient(
    private val deckRepository: DeckRepository
) {

    private val socket = Socket()
    private lateinit var inputStream: DataInputStream
    private lateinit var outputStream: DataOutputStream

    fun connect(socketParams: SocketParams) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                socket.connect(
                    InetSocketAddress(socketParams.host, socketParams.port), socketParams.timeout
                )
                inputStream = DataInputStream(socket.getInputStream())
                outputStream = DataOutputStream(socket.getOutputStream())
                val commands = inputStream.readUTF()
                withContext(Dispatchers.Main) {
                    deckRepository.parseCommands(commands)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                //TODO: notify about problems with connection
            }
        }
    }

    fun disconnect() {
        socket.close()
    }

    fun sendCommand(deckCommand: DeckCommand) {
        if (socket.isClosed) {
            throw SocketException("Socket closed")
        }
        GlobalScope.launch(Dispatchers.IO) {
            if (deckCommand.isDebug) {
                outputStream.writeUTF("click Debug-${deckCommand.name}")
            } else {
                outputStream.writeUTF("click Run-${deckCommand.name}")
            }
            outputStream.flush()
        }
    }
}
