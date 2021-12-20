package hu.bme.aut.androidlight.Settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.androidlight.R
import hu.bme.aut.androidlight.controller.lightController
import hu.bme.aut.androidlight.controller.udpSender.udpSender
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        ipAddres.setText(udpSender.ip)
        ipPort.setText(udpSender.port.toString())
        ledNumber.setText(lightController.ledNumber.toString())
        btnSave.setOnClickListener(){
            udpSender.ip = ipAddres.text.toString()
            Toast.makeText(this, "IP has set to address\n"+ udpSender.ip, Toast.LENGTH_SHORT).show()
            finish();
            udpSender.port = ipPort.text.toString().toInt()
            lightController.ledNumber = ledNumber.text.toString().toInt()
            udpSender.send("Length,"+lightController.ledNumber)
        }
    }
}