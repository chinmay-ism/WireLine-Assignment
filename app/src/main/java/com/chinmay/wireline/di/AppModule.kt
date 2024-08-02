package com.chinmay.wireline.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.chinmay.wireline.data.local.PexelDatabase
import com.chinmay.wireline.data.local.PexelEntity
import com.chinmay.wireline.data.remote.PexelApi
import com.chinmay.wireline.data.remote.PexelRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePexelDatabase(@ApplicationContext context: Context): PexelDatabase {
        return Room.databaseBuilder(
            context,
            PexelDatabase::class.java,
            "Pexels.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePexelApi(): PexelApi {
        return Retrofit.Builder()
            .baseUrl(PexelApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePexelPager(pexelDb: PexelDatabase, pexelApi: PexelApi): Pager<Int, PexelEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PexelRemoteMediator(
                pexelDb = pexelDb,
                pexelApi = pexelApi
            ),
            pagingSourceFactory = {
                pexelDb.dao.pagingSource()
            }
        )
    }
}