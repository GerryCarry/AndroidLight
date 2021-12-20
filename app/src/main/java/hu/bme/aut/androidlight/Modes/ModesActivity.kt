package hu.bme.aut.androidlight.Modes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.androidlight.R
import hu.bme.aut.androidlight.controller.lightController
import kotlinx.android.synthetic.main.activity_modes.*

class ModesActivity : AppCompatActivity(){

    private var Fade = false
    private var Lightning = false
    private var Strobe = false
    private var Run = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modes)

        buttonFade.setOnClickListener(){

            stopAll()
            if(!Fade) {
                lightController.setMode("Fade")
                Fade=true
                Lightning=false
                Strobe = false
                Run = false
            }
            else{
                Fade = false
                Lightning = false
                Strobe = false
                Run = false
            }
        }
        buttonLightning.setOnClickListener(){

            stopAll()
            if(!Lightning) {
                lightController.setMode("Lightning")
                Lightning=true
                Fade = false
                Strobe = false
                Run = false
            }
            else{
                Lightning=false
                Fade = false
                Strobe = false
                Run = false
            }
        }
        buttonStrobe.setOnClickListener(){

            stopAll()
            if(!Strobe) {
                lightController.setMode("Strobe")
                Lightning=false
                Fade = false
                Strobe = true
                Run = false
            }
            else{
                Lightning=false
                Fade = false
                Strobe = false
                Run = false
            }
        }
        buttonRun.setOnClickListener(){

            stopAll()
            if(!Run) {
                lightController.setMode("Run")
                Lightning=false
                Fade = false
                Strobe = false
                Run = true
            }
            else{
                Lightning=false
                Fade = false
                Strobe = false
                Run = false
            }
        }
    }

    fun stopAll() {
        if (Fade) {
            lightController.setMode("StopFade");
        }
        if (Lightning) {
            lightController.setMode("StopLightning")
        }
        if (Strobe) {
            lightController.setMode("StopStrobe")
        }
        if (Run) {
            lightController.setMode("StopRun")

        }
    }

}
