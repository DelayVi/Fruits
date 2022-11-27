package ru.delayvi.fruits.data.impl

import kotlinx.coroutines.flow.*
import ru.delayvi.fruits.data.database.fruits.AccountsFruitsSettingsDbEntity
import ru.delayvi.fruits.data.database.fruits.FruitsDao
import ru.delayvi.fruits.domain.accounts.AccountsRepository
import ru.delayvi.fruits.domain.fruits.FruitsRepository
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings

class FruitsRepositoryImpl(
    private val accountsRepository: AccountsRepository,
    private val fruitsDao: FruitsDao
) : FruitsRepository {

    override suspend fun getFruitsSettings(onlyActive: Boolean): Flow<List<FruitSettings>> {
        return accountsRepository.getAccount()
            .flatMapLatest { account ->
                if (account == null) return@flatMapLatest flowOf(emptyList())
                queryFruitsAndSettings(account.id)
            }
            .mapLatest { fruitSettings ->
                if (onlyActive) {
                    fruitSettings.filter { it.isActive }
                } else {
                    fruitSettings
                }
            }
    }

    override suspend fun getFruitById(id: Long): Fruit {
        TODO("Not yet implemented")
    }

    override suspend fun activateFruit(fruit: Fruit) {
        changeFlagFruitActivation(fruit, true)
    }

    override suspend fun deactivateFruit(fruit: Fruit) {
        changeFlagFruitActivation(fruit, false)
    }

    private fun queryFruitsAndSettings(accountId: Long): Flow<List<FruitSettings>> {
        return fruitsDao.getFruitsAndSettings(accountId).map { entity ->
            entity.map {
                val fruit = it.key
                val settings = it.value
                FruitSettings(fruit.toFruit(), settings.isActive)
            }
        }
    }

    private suspend fun changeFlagFruitActivation(fruit: Fruit, isActive: Boolean) {
        accountsRepository.getAccount().first().let {
            if (it != null) {
                fruitsDao.changeFlagFruitActivation(
                    AccountsFruitsSettingsDbEntity(
                        accountId = it.id,
                        fruitId = fruit.id,
                        isActive = isActive
                    )
                )
            }
        }
    }
}