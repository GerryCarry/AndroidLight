package hu.bme.aut.androidlight.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipeitem")
data class RecipeItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "Red") val Red: Int,
    @ColumnInfo(name = "Green") val Green: Int,
    @ColumnInfo(name = "Blue") val Blue: Int
)