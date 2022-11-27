package ru.delayvi.fruits.data.database.accounts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE email =:email LIMIT 1")
    suspend fun findByEmail(email: String): AccountDbEntity

    @Update(entity = AccountDbEntity::class)
    suspend fun updateUsername(account: UpdateUsernameTuple)

    @Insert
    suspend fun createAccount(accountDbEntity: AccountDbEntity)

    @Query("SELECT * FROM accounts WHERE id=:id")
    fun getById(id: Long): Flow<AccountDbEntity?>
}