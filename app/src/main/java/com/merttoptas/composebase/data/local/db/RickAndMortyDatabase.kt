package com.merttoptas.composebase.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merttoptas.composebase.data.local.converter.EpisodeConverter
import com.merttoptas.composebase.data.local.dao.FavoriteDao
import com.merttoptas.composebase.data.model.FavoriteEntity

/**
 * Created by merttoptas on 27.03.2022
 */

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(EpisodeConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}