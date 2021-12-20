package hu.bme.aut.androidlight.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.androidlight.R
import hu.bme.aut.androidlight.data.RecipeItem
import hu.bme.aut.androidlight.controller.lightController

class RecipeAdapter(private val listener: RecipeItemClickListener) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val items = mutableListOf<RecipeItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = items[position]

        holder.colorTemplate.setBackgroundColor(Color.argb(255, item.Red, item.Green,item.Blue))
        holder.item = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: RecipeItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(RecipeItem: List<RecipeItem>) {
        items.clear()
        items.addAll(RecipeItem)
        notifyDataSetChanged()
    }

    fun itemChange(item:RecipeItem){

    }

    interface RecipeItemClickListener {
        fun onItemChanged(item: RecipeItem)
        fun onRecipeItemDelete(item: RecipeItem)
        fun onLongClick(item:RecipeItem)
        fun onPowerButtonChange()
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val colorTemplate: LinearLayout
        val removeButton: ImageButton

        var item: RecipeItem? = null

        init {
            colorTemplate = itemView.findViewById(R.id.ColorTemplate)
            removeButton = itemView.findViewById(R.id.RemoveButton)

            removeButton.setOnClickListener(View.OnClickListener() {
                listener.onRecipeItemDelete(items.get(this.adapterPosition))
                items.removeAt(this.adapterPosition)
                notifyItemRemoved(this.adapterPosition)

            })

            colorTemplate.setOnLongClickListener(){
                listener.onLongClick(items.get(this.adapterPosition))
                true
            }

            colorTemplate.setOnClickListener(){
                lightController.setColor(items.get(this.adapterPosition).Red,items.get(this.adapterPosition).Green,items.get(this.adapterPosition).Blue)
                listener.onPowerButtonChange()
            }
        }
    }
}