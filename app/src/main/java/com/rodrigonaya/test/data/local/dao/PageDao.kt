package com.rodrigonaya.test.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rodrigonaya.test.data.local.entity.Page
import kotlinx.coroutines.flow.Flow

@Dao
interface PageDao {

    @Upsert
    suspend fun upsertPage(page: Page)

    @Query("SELECT * FROM Page LIMIT 1")
    suspend fun getPageData(): Page?
}