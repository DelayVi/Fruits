package ru.delayvi.fruits.data.database.fruits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitsDao {

    @Query("SELECT * FROM fruits LEFT JOIN accounts_fruits_settings " +
            "ON fruits.id = accounts_fruits_settings.fruit_id " +
            "AND accounts_fruits_settings.account_id =:accountId")
    fun getFruitsAndSettings(accountId: Long): Flow<Map<FruitDbEntity, AccountsFruitsSettingsDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeFlagFruitActivation(accountsFruitsSettingsDbEntity: AccountsFruitsSettingsDbEntity)
}