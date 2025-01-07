package com.elorrieta.alumnoclient.socketIO

import android.app.Activity
import android.util.Log
import android.widget.TextView
import com.elorrieta.alumnoclient.R
import com.elorrieta.alumnoclient.entity.Alumno
import com.elorrieta.socketsio.sockets.config.Events
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket


/**
 * The client
 */
class SocketClient (private val activity: Activity) {

    // Server IP:Port
    private val ipPort = "http://10.0.2.2:3000"
    private val socket: Socket = IO.socket(ipPort)

    // For log purposes
    private var tag = "socket.io"

    // We add here ALL the events we are eager to LISTEN from the server
    init {
        // Event called when the socket connects
        socket.on(Socket.EVENT_CONNECT) {
            Log.d(tag, "Connected...")
        }

        // Event called when the socket disconnects
        socket.on(Socket.EVENT_DISCONNECT) {
            Log.d(tag, "Disconnected...")
        }

        // Event called when the socket gets an answer from a login attempt.
        // We get the message and print it. Note: this event is called after
        // We try to login
    }

    // Default events
    // This method is called when we want to establish a connection with the server
    fun connect() {
        socket.connect()

        // Log traces
        activity.findViewById<TextView>(R.id.textView).append("\n" + "Connecting to server...")
        Log.d (tag, "Connecting to server...")
    }

    // This method is called when we want to disconnect from the server
    fun disconnect() {
        socket.disconnect()

        // Log traces
        activity.findViewById<TextView>(R.id.textView).append("\n" + "Disconnecting from server...")
        Log.d (tag, "Disconnecting from server...")
    }

    // Custom events
    fun
            buscarTelefono(nombre: String, apellido: String) {
        val alumno = Alumno(0, nombre, apellido, "2777227272", 644894751)
        socket.emit(Events.ON_BUSCAR_TELEFONO.value, Gson().toJson(alumno))
    }
}
