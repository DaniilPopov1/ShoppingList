package com.example.shoppinglist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.data.database.model.CryptoItemDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoInfoDao {

    @Query("SELECT * FROM crypto_info")
    fun getCryptoList(): Flow<List<CryptoItemDbModel>>

    @Query("SELECT * FROM crypto_info WHERE id == :id LIMIT 1")
    fun getCryptoItem(id: String): Flow<CryptoItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoList(cryptoList: List<CryptoItemDbModel>)
}