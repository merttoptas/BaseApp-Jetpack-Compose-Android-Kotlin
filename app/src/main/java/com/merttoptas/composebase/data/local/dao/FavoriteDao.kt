package com.merttoptas.composebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.merttoptas.composebase.data.local.base.BaseDao
import com.merttoptas.composebase.data.model.FavoriteEntity
import com.merttoptas.composebase.data.remote.utils.Constants

/**
 * Created by merttoptas on 27.03.2022
 */

@Dao
interface FavoriteDao : BaseDao<FavoriteEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    suspend fun getFavoriteList(): List<FavoriteEntity>

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity?

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${Constants.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}