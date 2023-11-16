package com.kashapovrush.cardbinrequest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardInfoDao {

    @Query("SELECT * FROM cards")
    fun getCardInfoList(): Flow<List<CardInfoDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardInfoItem(cardInfoDb: CardInfoDb)
}