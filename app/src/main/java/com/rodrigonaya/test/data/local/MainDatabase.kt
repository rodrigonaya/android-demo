package com.rodrigonaya.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigonaya.test.data.local.dao.PageDao
import com.rodrigonaya.test.data.local.entity.Page

@Database(entities = [Page::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun pageDao(): PageDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}