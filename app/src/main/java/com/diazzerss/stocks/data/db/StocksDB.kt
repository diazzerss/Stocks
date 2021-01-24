
package com.diazzerss.stocks.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diazzerss.stocks.domain.model.Stock
import com.diazzerss.stocks.domain.model.StockDao
/*
@Database(entities = [Stock::class], version = 1)
abstract class StocksDatabase : RoomDatabase() {

    abstract fun stockDao(): StockDao

    companion object {
        private var INSTANCE: StocksDatabase? = null
        private const val DB_NAME = "stocks.db"

        fun getDatabase(context: Context): StocksDatabase {
            if (INSTANCE == null) {
                synchronized(StocksDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, StocksDatabase::class.java, DB_NAME)
                                .build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}*/
