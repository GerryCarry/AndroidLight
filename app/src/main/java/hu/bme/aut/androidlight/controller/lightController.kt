package hu.bme.aut.androidlight.controller

import hu.bme.aut.androidlight.controller.udpSender.udpSender

object lightController {
    private var onPower = false
     var ledNumber: Int = 30
        get() = field
        set(value) {
            field = value
        }

    fun setColor(red:Int, green: Int,blue: Int){
        onPower = true
        udpSender.send(red,green,blue)
    }

    fun setMode(command :String){
        udpSender.send(command)
    }

    fun powerOn(){

        if(!onPower){
            onPower = true
            udpSender.send(255,255,255)
        }
        else{
            onPower = false
            udpSender.send(0,0,0)
        }
    }

    fun isOn():Boolean{
        return onPower
    }

    interface turnOnListener{
        fun turnOnButtonChange()
    }

}