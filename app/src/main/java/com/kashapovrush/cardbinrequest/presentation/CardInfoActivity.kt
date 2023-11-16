package com.kashapovrush.cardbinrequest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kashapovrush.cardbinrequest.databinding.ActivityCardInfoBinding
import javax.inject.Inject

class CardInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardInfoBinding
    private lateinit var viewModel: CardInfoViewModel
    private var cardId: Int = 0

    private val component by lazy {
        (application as CardInfoApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[CardInfoViewModel::class.java]

        cardId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.getCardItem(cardId)
        viewModel.showCardItem.observe(this) {
            with(binding) {
                luhnField.text = it?.number?.luhn.toString()
                schemeField.text = it?.scheme
                typeField.text = it?.type
                brandField.text = it?.brand
                prepaidField.text = it?.prepaid.toString()
                luhnField.text = it?.number?.luhn.toString()
                lengthField.text = it?.number?.length.toString()
                numericField.text = it?.country?.numeric
                alphaField.text = it?.country?.alpha2
                nameCountryField.text = it?.country?.name
                emojiField.text = it?.country?.emoji
                currencyField.text = it?.country?.currency
                longitudeField.text = it?.country?.longitude.toString()
                latitudeField.text = it?.country?.latitude.toString()
                nameBankField.text = it?.bank?.name
                urlField.text = it?.bank?.url
                phoneField.text = it?.bank?.phone
                cityField.text = it?.bank?.city
            }
        }

    }



    companion object {

        private const val EXTRA_ID = "id"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, CardInfoActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}