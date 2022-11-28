package ru.delayvi.fruits.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.Provides
import ru.delayvi.fruits.data.database.accounts.AccountDbEntity
import ru.delayvi.fruits.data.database.accounts.AccountsDao
import ru.delayvi.fruits.data.database.fruits.AccountsFruitsSettingsDbEntity
import ru.delayvi.fruits.data.database.fruits.FruitDbEntity
import ru.delayvi.fruits.data.database.fruits.FruitsDao

@Database(
    version = 1,
    entities = [
        AccountDbEntity::class,
        FruitDbEntity::class,
        AccountsFruitsSettingsDbEntity::class
    ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getFruitsDao(): FruitsDao

}