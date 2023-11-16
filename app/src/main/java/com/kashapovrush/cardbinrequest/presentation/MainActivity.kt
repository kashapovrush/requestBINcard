package com.kashapovrush.cardbinrequest.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kashapovrush.cardbinrequest.databinding.ActivityMainBinding
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var callback: Callback<CardInfoMain>

    private lateinit var longitudeFieldText: String
    private lateinit var latitudeFieldText: String
    private lateinit var urlFieldText: String
    private lateinit var phoneFieldText: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CardInfoApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCallback()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.searchButton.setOnClickListener {
            viewModel.getCardInfo(binding.searchId.text.toString(), callback)
            viewModel.addCardInfoItem(binding.searchId.text.toString())
        }

        binding.phoneField.setOnClickListener {
            viewModel.intentToCall(phoneFieldText, this)
        }

        binding.cityField.setOnClickListener {
            viewModel.intentGoToMap(latitudeFieldText, longitudeFieldText, this)
        }

        binding.urlField.setOnClickListener {
            viewModel.intentGOToSite(urlFieldText, this)
        }

        binding.buttonRequestHistory.setOnClickListener {
            startActivity(RequestHistoryActivity.newIntent(this))
        }
    }

    private fun setCallback() {
        callback = object : Callback<CardInfoMain> {
            override fun onResponse(call: Call<CardInfoMain>, response: Response<CardInfoMain>) {

                setVisibilityResult()
                setViews(response)
            }

            override fun onFailure(call: Call<CardInfoMain>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setVisibilityResult() {
        binding.resultLayout.visibility = View.VISIBLE
    }

    private fun setViews(response: Response<CardInfoMain>) {
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
}