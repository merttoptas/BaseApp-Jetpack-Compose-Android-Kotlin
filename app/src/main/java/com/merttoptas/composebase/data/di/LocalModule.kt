package com.merttoptas.composebase.data.di

import android.content.Context
import androidx.room.Room
import com.merttoptas.composebase.data.local.dao.FavoriteDao
import com.merttoptas.composebase.data.local.db.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by merttoptas on 27.03.2022
 */

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(
        @ApplicationContext context: Context
    ): RickAndMortyDatabase {
        return RickAndMortyDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: RickAndMortyDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}