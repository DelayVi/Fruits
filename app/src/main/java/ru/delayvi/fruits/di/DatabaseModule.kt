package ru.delayvi.fruits.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.delayvi.fruits.data.database.AppDatabase
import ru.delayvi.fruits.data.database.accounts.AccountsDao
import ru.delayvi.fruits.data.database.fruits.FruitsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "main.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideAccountsDao(db: AppDatabase): AccountsDao {
        return db.getAccountsDao()
    }

    @Provides
    @Singleton
    fun provideFruitsDao(db: AppDatabase): FruitsDao {
        return db.getFruitsDao()
    }
}