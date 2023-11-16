package com.kashapovrush.cardbinrequest.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.kashapovrush.cardbinrequest.data.database.CardInfoDao
import com.kashapovrush.cardbinrequest.data.mapper.CardMapper
import com.kashapovrush.cardbinrequest.data.network.ApiService
import com.kashapovrush.cardbinrequest.domain.CardBINRepository
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val cardMapper: CardMapper,
    private val cardInfoDao: CardInfoDao
): CardBINRepository {


    override fun getCardInfo(number: String): Call<CardInfoMain> {
        return apiService.getCardInfo(number)
    }

    override fun intentToCall(inputPhoneNumber: String, context: Context) {
        val intent = Intent(Intent.ACTION_DIAL)
        val phoneNumber = Uri.parse("tel:$inputPhoneNumber")
        intent.data = phoneNumber
        context.startActivity(intent)
    }

    override fun intentGoToMap(latitude: String, longitude: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)

        val addressUri = Uri.parse("geo:$latitude,$longitude")
        intent.data = addressUri

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    override fun intentGoToSite(url: String, context: Context) {
        openWebPage("https://$url", context)
    }

    override fun getCardInfoList(): Flow<List<CardInfoMain>> {
        return cardInfoDao.getCardInfoList().map {
            cardMapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun addCardInfoItem(cardInfoMain: CardInfoMain) {
        cardInfoDao.addCardInfoItem(cardMapper.mapEntityToDbModel(cardInfoMain))
    }

    override suspend fun deleteCardInfo(cardInfoMain: CardInfoMain) {
        cardInfoDao.deleteCardInfo(cardInfoMain.id)
    }

    override suspend fun getCardItem(id: String): CardInfoMain {
        return cardMapper.mapDbModelToEntity(cardInfoDao.getCardItem(id))
    }

    fun openWebPage(url: String, context: Context) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
        context.startActivity(intent)
    }
}
