package ru.delayvi.fruits.data.database.fruits

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.Nutritions

@Entity(tableName = "fruits")
data class FruitDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "fruit_name")
    val fruitName: String,
) {
    fun toFruit() = Fruit(
        id = id,
        fruitName = fruitName
    )
}
