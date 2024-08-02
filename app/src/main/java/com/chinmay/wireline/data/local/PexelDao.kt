package com.chinmay.wireline.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PexelDao {

    @Upsert
    suspend fun upsertAll(pexels: List<PexelEntity>)

    @Query("SELECT * FROM pexelentity")
    fun pagingSource(): PagingSource<Int, PexelEntity>

    @Query("DELETE FROM pexelentity")
    suspend fun clearAll()
}