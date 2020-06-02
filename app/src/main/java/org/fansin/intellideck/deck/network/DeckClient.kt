package org.fansin.intellideck.deck.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fansin.intellideck.deck.domain.ConnectionObservable
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
    private val deckRepository: DeckRepository,
    private val connectionObservable: ConnectionObservable
) {

    private var socket = Socket()
    private var triedToReconnect = false
    private lateinit var inputStream: DataInputStream
    private lateinit var outputStream: DataOutputStream

    var isConnected = false
        private set(value) {
            field = value
            if (value) {
                GlobalScope.launch(Dispatchers.Main) {
                    connectionObservable.onConnected()
                }
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    connectionObservable.onDisconnected()
                }
            }
        }

    fun connect(socketParams: SocketParams) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                socket.connect(
                    InetSocketAddress(socketParams.host, socketParams.port), socketParams.timeout
                )
                isConnected = true
                inputStream = DataInputStream(socket.getInputStream())
                outputStream = DataOutputStream(socket.getOutputStream())
                val commands = inputStream.readUTF()
                withContext(Dispatchers.Main) {
                    if (commands.startsWith("Tasks")) {
                        deckRepository.parseCommands(commands)
                    }
                }
                startListening()
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

    fun disconnect() {
        socket.close()
        socket = Socket()
        connectionObservable.onDisconnected()
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

    private fun startListening() = GlobalScope.launch(Dispatchers.IO) {
        while (isConnected) {
            val str = try {
                inputStream.readUTF()
            } catch (ioException: IOException) {
                isConnected = false
                break
            }
            if (str == "Plugin requested stopping server") {
                isConnected = false
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
}
