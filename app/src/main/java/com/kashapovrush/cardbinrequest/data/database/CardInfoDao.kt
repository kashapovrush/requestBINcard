package com.kashapovrush.cardbinrequest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow

@Dao
interface CardInfoDao {

    @Query("SELECT * FROM cards")
    fun getCardInfoList(): Flow<List<CardInfoDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardInfoItem(cardInfoDb: CardInfoDb)

    @Query("DELETE FROM cards WHERE id=:id")
    suspend fun deleteCardInfo(id: Int)

    @Query("SELECT * FROM cards WHERE id=:id LIMIT 1")
    suspend fun getCardItem(id: Int): CardInfoDb
}