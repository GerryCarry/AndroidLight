package hu.bme.aut.androidlight.data

import androidx.room.*

@Dao
interface RecipeItemDao {
    @Query("SELECT * FROM recipeitem")
    fun getAll(): List<RecipeItem>

    @Insert
    fun insert(RecipeItem: RecipeItem): Long

    @Update
    fun update(RecipeItem: RecipeItem)

    @Delete
    fun deleteItem(RecipeItem: RecipeItem)
}