package ru.delayvi.fruits.data.impl

import android.util.Log
import androidx.lifecycle.Transformations.map
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.flow.*
import okhttp3.Cache.Companion.key
import ru.delayvi.fruits.data.database.fruits.AccountsFruitsSettingsDbEntity
import ru.delayvi.fruits.data.database.fruits.FruitsDao
import ru.delayvi.fruits.data.network.FruitsApi
import ru.delayvi.fruits.domain.accounts.AccountsRepository
import ru.delayvi.fruits.domain.fruits.FruitsRepository
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings
import ru.delayvi.fruits.domain.fruits.entity.Nutritions
import javax.inject.Inject

@BoundTo(supertype = FruitsRepository::class, component = SingletonComponent::class)
class FruitsRepositoryImpl @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val fruitsDao: FruitsDao,
    private val fruitsApi: FruitsApi
) : FruitsRepository {

    private val fruits = MutableStateFlow<List<Fruit>>(listOf())

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

    override suspend fun activateFruit(fruit: Fruit) {
        changeFlagFruitActivation(fruit, true)
    }

    override suspend fun deactivateFruit(fruit: Fruit) {
        changeFlagFruitActivation(fruit, false)
    }

    override suspend fun getNutritions(fruitName: String): Nutritions {
        return  fruitsApi.getFruit(fruitName).toNutritions()
    }

    private suspend fun queryFruitsAndSettings(accountId: Long): Flow<List<FruitSettings>> {
        getAllFruits()
        return fruitsDao.getFruitsAndSettings(accountId).map { entity ->
            entity.map {
                val fruit = it.key
                val settings = it.value
                FruitSettings(fruit.toFruit(), settings == null || settings.isActive)
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

    private suspend fun getAllFruits() {
        getFruitList()
        if (fruits.value == emptyList<Any>()) {
            updateFruitList()
            getFruitList()
        }
    }

    private suspend fun updateFruitList() {
        fruitsApi.getAllFruits().map { fruitsDao.loadFruit(it.toFruitDbEntity()) }
    }

    private suspend fun getFruitList() {
        val fruitList = fruitsDao.getAllFruits().map {
            it.toFruit()
        }
        fruits.value = fruitList
    }
}