package com.chinmay.wireline.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.chinmay.wireline.data.local.PexelDatabase
import com.chinmay.wireline.data.local.PexelEntity
import com.chinmay.wireline.data.mappers.toPexelEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PexelRemoteMediator(
    private val pexelDb: PexelDatabase,
    private val pexelApi: PexelApi
): RemoteMediator<Int, PexelEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PexelEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val currentPage = state.pages.size

                    // Calculate the next page index
                    currentPage + 1
                }
            }

            val response = pexelApi.getPexels(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            pexelDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pexelDb.dao.clearAll()
                }
                val pexelEntities = response.photos.map { it.toPexelEntity() }
                pexelDb.dao.upsertAll(pexelEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.next_page == null
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
