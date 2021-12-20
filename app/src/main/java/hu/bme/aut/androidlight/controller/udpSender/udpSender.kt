package hu.bme.aut.androidlight.controller.udpSender

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

object udpSender{

    var ip = "192.168.0.12"
        get() = field
        set(value) {
            field = value
        }


    var port = 11000
        get() = field
        set(value) {
            field = value
        }

    fun send( red: Int, green: Int, blue: Int) {
        Thread({
            println(ip)
            val msg = red.toString() +","+ green.toString() +","+ blue.toString()
            val socket = DatagramSocket()

            val serverAddr = InetAddress.getByName(ip)
            val message = msg.toByteArray()
            val p = DatagramPacket(message, msg.length, serverAddr, port)
            socket.send(p)

        }).start()
    }

    fun send(msg:String){
        Thread({
            println(ip)
            val socket = DatagramSocket()
            val serverAddr = InetAddress.getByName(ip)
            val message = msg.toByteArray()
            val p = DatagramPacket(message, msg.length, serverAddr, port)
            socket.send(p)
        }).start()
    }
}