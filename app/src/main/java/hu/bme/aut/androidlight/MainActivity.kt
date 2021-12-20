package hu.bme.aut.androidlight

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fragment.ColorPickerdialogFragment
import hu.bme.aut.androidlight.Modes.ModesActivity
import hu.bme.aut.androidlight.Settings.SettingsActivity
import hu.bme.aut.androidlight.adapter.RecipeAdapter
import hu.bme.aut.androidlight.controller.lightController
import hu.bme.aut.androidlight.data.RecipeItem
import hu.bme.aut.androidlight.data.RecipeItemDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), RecipeAdapter.RecipeItemClickListener,
    ColorPickerdialogFragment.demoFieldClickListener,
    ColorPickerdialogFragment.NewRecipeItemDialogListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var database: RecipeItemDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabAdd.setOnClickListener(){
            var dialog: ColorPickerdialogFragment = ColorPickerdialogFragment(this)
            dialog.show(supportFragmentManager, "DATE_TAG")
        }
        database = Room.databaseBuilder(
            applicationContext,
            RecipeItemDatabase::class.java,
            "recipe"
        ).build()

        power_button.setOnClickListener(){
            lightController.powerOn()
            if(lightController.isOn()){
               power_button.setImageResource(R.drawable.lighting_bulb)
            }
            else{
                power_button.setImageResource(R.drawable.power_on)
            }
        }

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.modes -> {
                startActivity(Intent(this, ModesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    ///INNEN RecycleView
    private fun initRecyclerView() {
        recyclerView = MainRecyclerView
        adapter = RecipeAdapter(this)
        loadItemsInBackground()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.RecipeItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: RecipeItem) {
        thread {
            database.RecipeItemDao().update(item)
            Log.d("MainActivity", "RecipeItem update was successful")
            loadItemsInBackground()
        }

    }

    override fun onRecipeItemCreated(newItem: RecipeItem) {
        thread {
            val newId = database.RecipeItemDao().insert(newItem)
            val newRecipeItem = newItem.copy(
                id = newId
            )
            runOnUiThread {
                adapter.addItem(newRecipeItem)
            }
        }
    }

    override fun onRecipeItemDelete(item: RecipeItem){
        thread {
            database.RecipeItemDao().deleteItem(item)
        }
    }

    override fun onLongClick(Recipe: RecipeItem) {
        val dialog: ColorPickerdialogFragment = ColorPickerdialogFragment(this)
        dialog.setValues(Recipe)
        dialog.show(supportFragmentManager, "DATE_TAG")
    }

    override fun onPowerButtonChange(){
        power_button.setImageResource(R.drawable.lighting_bulb)
    }

    override fun demoFieldClicked() {
        power_button.setImageResource(R.drawable.lighting_bulb)
    }
}