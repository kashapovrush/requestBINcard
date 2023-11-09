package com.kashapovrush.cardbinrequest.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kashapovrush.cardbinrequest.domain.CardInfo
import com.kashapovrush.cardbinrequest.data.network.ApiFactory
import com.kashapovrush.cardbinrequest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var urlFieldText: String? = null
    var latitudeFieldText: String? = null
    var longitudeFieldText: String? = null
    var phoneFieldText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val api = ApiFactory.apiService
            val info = api.getCardInfo(binding.searchId.text.toString())
            info.enqueue(object : Callback<CardInfo> {
                override fun onResponse(call: Call<CardInfo>, response: Response<CardInfo>) {
                    setVisibilityResult()
                    setViews(response)

                }
                override fun onFailure(call: Call<CardInfo>, t: Throwable) {
                    Log.d("MainActivityTest", "Error")
                }

            })
        }
    }

    private fun setVisibilityResult() {
        binding.resultLayout.visibility = View.VISIBLE
    }

    private fun setViews(response: Response<CardInfo>) {
        binding.apply {
            schemeField.text = validateInputText(response.body()?.scheme)
            lengthField.text = validateInputNumber(response.body()?.number?.length)
            luhnField.text = validateInputBoolean(response.body()?.number?.luhn)
            typeField.text = validateInputText(response.body()?.type)
            brandField.text = validateInputText(response.body()?.brand)
            prepaidField.text = validateInputBoolean(response.body()?.prepaid)
            numericField.text = validateInputText(response.body()?.country?.numeric)
            alphaField.text = validateInputText(response.body()?.country?.alpha2)
            nameCountryField.text = validateInputText(response.body()?.country?.name)
            emojiField.text = validateInputText(response.body()?.country?.emoji)
            currencyField.text = validateInputText(response.body()?.country?.currency)

            longitudeFieldText = validateInputNumber(response.body()?.country?.longitude)
            longitudeField.text = longitudeFieldText

            latitudeFieldText = validateInputNumber(response.body()?.country?.latitude)
            latitudeField.text = latitudeFieldText

            nameBankField.text = validateInputText(response.body()?.bank?.name)

            urlFieldText = validateInputText(response.body()?.bank?.url)
            urlField.text = urlFieldText

            phoneFieldText = validateInputText(response.body()?.bank?.phone)
            phoneField.text = phoneFieldText
            cityField.text = validateInputText(response.body()?.bank?.city)
        }
    }

    private fun validateInputText(text: String?): String {
        if (text == null) {
            return ""
        }
        return text
    }

    private fun validateInputNumber(number: Int?): String {
        if (number == null) {
            return ""
        }
        return number.toString()
    }

    private fun validateInputBoolean(boolean: Boolean?): String {
        if (boolean == true) {
            return "YES"
        }
        return "NO"
    }

    fun intentToCall(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        val phoneNumber = Uri.parse("tel:$phoneFieldText")
        intent.data = phoneNumber
        startActivity(intent)
    }

    fun intentGoToMap(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)

        val addressUri = Uri.parse("geo:$latitudeFieldText,$longitudeFieldText")
        intent.data = addressUri

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun intentGoToSite(view: View) {
        openWebPage("https://$urlFieldText")
    }

    fun openWebPage(url: String?) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        startActivity(intent)
    }
}