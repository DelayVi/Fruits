package ru.delayvi.fruits.data.database.fruits

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import ru.delayvi.fruits.data.database.accounts.AccountDbEntity

@Entity(
    tableName = "accounts_fruits_settings",
    foreignKeys = [
        ForeignKey(
            entity = AccountDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FruitDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["fruit_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ],
    primaryKeys = ["account_id", "fruit_id"],
    indices = [
        Index("fruit_id")
    ]
)
data class AccountsFruitsSettingsDbEntity(
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @ColumnInfo(name = "fruit_id")
    val fruitId: Long,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean
)
