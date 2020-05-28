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
import java.net.SocketTimeoutException

class DeckClient(
    private val deckRepository: DeckRepository
) {

    private var socket = Socket()
    private var triedToReconnect = false
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
                    if (commands.startsWith("Tasks")) {
                        deckRepository.parseCommands(commands)
                    }
                }
            } catch (timeOutException: SocketTimeoutException) {
                socket = Socket()
                reconnectOnce(socketParams)
            } catch (socketException: SocketException) {
                socket = Socket()
                reconnectOnce(socketParams)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
        }
    }

    private fun reconnectOnce(socketParams: SocketParams) {
        if (!triedToReconnect) {
            triedToReconnect = true
            connect(socketParams)
            triedToReconnect = false
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
