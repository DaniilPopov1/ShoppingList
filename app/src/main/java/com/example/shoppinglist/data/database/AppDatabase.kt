package com.example.shoppinglist.data.database

import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.shoppinglist.data.database.model.CryptoItemDbModel

@Database(entities = [CryptoItemDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    companion object{

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK){
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun cryptoInfoDao(): CryptoInfoDao
}