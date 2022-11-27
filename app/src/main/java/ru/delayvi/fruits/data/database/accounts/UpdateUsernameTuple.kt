package ru.delayvi.fruits.data.database.accounts

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UpdateUsernameTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "username") val username: String
)