package com.merttoptas.composebase.data.di

import android.content.Context
import com.merttoptas.composebase.RickAndMortyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by merttoptas on 25.03.2022
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): RickAndMortyApp {
        return app as RickAndMortyApp
    }
}