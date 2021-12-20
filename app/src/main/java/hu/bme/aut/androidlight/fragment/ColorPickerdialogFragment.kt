package fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import hu.bme.aut.androidlight.R
import hu.bme.aut.androidlight.adapter.RecipeAdapter
import hu.bme.aut.androidlight.data.RecipeItem
import hu.bme.aut.androidlight.controller.lightController
import kotlinx.android.synthetic.main.dialog_fragment.*
import kotlinx.android.synthetic.main.dialog_fragment.view.*


public class ColorPickerdialogFragment(private val demoListener: demoFieldClickListener) : DialogFragment() {

    interface NewRecipeItemDialogListener {
        fun onRecipeItemCreated(newItem: RecipeItem)
        fun onItemChanged(item :RecipeItem)
    }
    private lateinit var listener: NewRecipeItemDialogListener

    var red = 0
    var green = 0
    var blue = 0
    var forEdit = false
    var recipeForEdit : RecipeItem = RecipeItem(0,0,0,0)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewRecipeItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewRecipeItemDialogListener interface!")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //return inflater.inflate(R.layout.dialog_fragment,container,false)
        val view: View = inflater!!.inflate(R.layout.dialog_fragment, container, false)

        view.seekBarRed.progress = red
        view.seekBarRed.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                red = seekBarRed.progress
                demoField.setBackgroundColor(Color.argb(255,red,green,blue))
                imageView.setImageResource(R.drawable.transparent)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })

        view.seekBarGreen.progress = green
        view.seekBarGreen.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                green = seekBarGreen.progress
                demoField.setBackgroundColor(Color.argb(255,red,green,blue))
                imageView.setImageResource(R.drawable.transparent)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
        view.seekBarBlue.progress = blue
        view.seekBarBlue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                blue = seekBarBlue.progress
                demoField.setBackgroundColor(Color.argb(255,red,green,blue))
                imageView.setImageResource(R.drawable.transparent)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })

        view.demoField.setBackgroundColor(Color.argb(255,red,green,blue))
        view.demoField.setOnClickListener(){
            lightController.setColor(red, green, blue)
            demoListener.demoFieldClicked()
            imageView.setImageResource(R.drawable.lighting_bulb)
        }


        view.btnSave.setOnClickListener { view ->
            if(forEdit){
                var changedRecipe : RecipeItem = RecipeItem(recipeForEdit.id,red,green,blue)
                listener.onItemChanged(changedRecipe)

            }
            else {
                listener.onRecipeItemCreated(getRecipeItem())
            }
            this.dismiss()
        }

        return view
    }

    private fun getRecipeItem() = RecipeItem(
        id = null,
        Red = this.red,
        Green = this.green,
        Blue = this.blue
    )

    fun setValues(Recipe: RecipeItem){
        red = Recipe.Red
        green = Recipe.Green
        blue = Recipe.Blue
        forEdit = true
        recipeForEdit = Recipe
    }

    interface demoFieldClickListener{
        fun demoFieldClicked()
    }

}

