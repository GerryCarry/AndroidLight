package hu.bme.aut.androidlight.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeItem::class], version = 1)
abstract class RecipeItemDatabase : RoomDatabase() {
    abstract fun  RecipeItemDao(): RecipeItemDao
}