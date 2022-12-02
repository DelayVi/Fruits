package ru.delayvi.fruits.data.database.fruits

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.delayvi.fruits.domain.fruits.entity.Fruit

@Dao
interface FruitsDao {

    @Query("SELECT * FROM fruits LEFT JOIN accounts_fruits_settings " +
            "ON fruits.id = accounts_fruits_settings.fruit_id " +
            "AND accounts_fruits_settings.account_id =:accountId")
    fun getFruitsAndSettings(accountId: Long): Flow<Map<FruitDbEntity, AccountsFruitsSettingsDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeFlagFruitActivation(accountsFruitsSettingsDbEntity: AccountsFruitsSettingsDbEntity)


    @Query("SELECT * FROM fruits")
    suspend fun getAllFruits(): List<FruitDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun loadFruit(fruit: FruitDbEntity)
}