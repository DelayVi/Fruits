package ru.delayvi.fruits.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.delayvi.fruits.data.network.ApiFactory.BASE_URL
import ru.delayvi.fruits.data.network.FruitsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideFruitsApi(retrofit: Retrofit): FruitsApi {
        return retrofit.create(FruitsApi::class.java)
    }
}