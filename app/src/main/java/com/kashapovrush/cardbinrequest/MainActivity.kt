package com.kashapovrush.cardbinrequest

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kashapovrush.cardbinrequest.NetworkUtil.Companion.generateURL
import com.kashapovrush.cardbinrequest.NetworkUtil.Companion.getResponseFromURL
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var searchField: EditText
    lateinit var searchButton: Button
    lateinit var schemeField: TextView
    lateinit var typeField: TextView
    lateinit var resultLayout: LinearLayout
    lateinit var brandField: TextView
    lateinit var lengthField: TextView
    lateinit var luhnField: TextView
    lateinit var numericField: TextView
    lateinit var alpha2Filed: TextView
    lateinit var nameCountryField: TextView
    lateinit var emojiField: TextView
    lateinit var currencyField: TextView
    lateinit var latitudeField: TextView
    lateinit var longitudeField: TextView
    lateinit var nameBankField: TextView
    lateinit var urlField: TextView
    lateinit var phoneField: TextView
    lateinit var cityField: TextView
    lateinit var prepaidField: TextView
    lateinit var preferenceManager: PreferenceManager
    var urlFieldText: String? = null
    var latitudeFieldText: String? = null
    var longitudeFieldText: String? = null
    var phoneFieldText: String? = null


    inner class BINQueryTask : AsyncTask<URL?, Void?, String?>() {

        override fun doInBackground(vararg params: URL?): String? {
            var response: String? = null
            try {
                response = getResponseFromURL(params[0])
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return response
        }

        override fun onPostExecute(response: String?) {
            var lengthFieldText: String? = null
            var schemeFieldText: String? = null
            var typeFieldText: String? = null
            var brandFieldText: String? = null
            var luhnFieldText: String? = null
            var numericFieldText: String? = null
            var alpha2FiledText: String? = null
            var nameCountryFieldText: String? = null
            var emojiFieldText: String? = null
            var currencyFieldText: String? = null
            var nameBankFieldText: String? = null
            var cityFieldText: String? = null
            var prepaidFieldText: String? = null
            if (response != null && response != "") {
                try {
                    val jsonObject = JSONObject(response)
                    schemeFieldText = jsonObject.getString("scheme")
                    typeFieldText = jsonObject.getString("type")
                    brandFieldText = jsonObject.getString("brand")
                    prepaidFieldText = jsonObject.getString("prepaid")

                    val jsonObjectNumber = jsonObject.getJSONObject("number")
                    lengthFieldText = jsonObjectNumber.getString("length")
                    luhnFieldText = jsonObjectNumber.getString("luhn")

                    val jsonObjectCountryInfo = jsonObject.getJSONObject("country")
                    numericFieldText = jsonObjectCountryInfo.getString("numeric")
                    alpha2FiledText = jsonObjectCountryInfo.getString("alpha2")
                    nameCountryFieldText = jsonObjectCountryInfo.getString("name")
                    emojiFieldText = jsonObjectCountryInfo.getString("emoji")
                    currencyFieldText = jsonObjectCountryInfo.getString("currency")
                    latitudeFieldText = jsonObjectCountryInfo.getString("latitude")
                    longitudeFieldText = jsonObjectCountryInfo.getString("longitude")

                    val jsonObjectBankInfo = jsonObject.getJSONObject("bank")
                    nameBankFieldText = jsonObjectBankInfo.getString("name")
                    urlFieldText = jsonObjectBankInfo.getString("url")
                    phoneFieldText = jsonObjectBankInfo.getString("phone")
                    cityFieldText = jsonObjectBankInfo.getString("city")

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                resultLayout.visibility = View.VISIBLE

                schemeField.text = schemeFieldText
                typeField.text = typeFieldText
                brandField.text = brandFieldText
                if (prepaidFieldText.equals("false")) {
                    prepaidField.text = "NO"
                } else {
                    prepaidField.text = "YES"
                }

                if (luhnFieldText.equals("false")) {
                    luhnField.text = "NO"
                } else {
                    luhnField.text = "YES"
                }

                lengthField.text = lengthFieldText

                numericField.text = numericFieldText
                alpha2Filed.text = alpha2FiledText
                nameCountryField.text = nameCountryFieldText
                emojiField.text = emojiFieldText
                currencyField.text = currencyFieldText
                latitudeField.text = latitudeFieldText
                longitudeField.text = longitudeFieldText

                nameBankField.text = nameBankFieldText
                urlField.text = urlFieldText
                phoneField.text = phoneFieldText
                cityField.text = cityFieldText

            } else {
                resultLayout.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        preferenceManager = PreferenceManager(applicationContext)
        val onClickListener = View.OnClickListener {
            val generatedURL: URL? = generateURL(searchField.text.toString())
            BINQueryTask().execute(generatedURL)
            preferenceManager.putString("key", searchField.text.toString())
        }
        searchButton.setOnClickListener(onClickListener)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.request_history) {
            val intent = Intent(this@MainActivity, RequestHistory::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        searchField = findViewById(R.id.search_id)
        searchButton = findViewById(R.id.search_button)
        schemeField = findViewById(R.id.scheme_field)
        typeField = findViewById(R.id.type_field)
        resultLayout = findViewById(R.id.result_layout)
        brandField = findViewById(R.id.brand_field)
        lengthField = findViewById(R.id.length_field)
        luhnField = findViewById(R.id.luhn_field)
        numericField = findViewById(R.id.numeric_field)
        alpha2Filed = findViewById(R.id.alpha2_field)
        nameCountryField = findViewById(R.id.name_country_field)
        emojiField = findViewById(R.id.emoji_field)
        currencyField = findViewById(R.id.currency_field)
        latitudeField = findViewById(R.id.latitude_field)
        longitudeField = findViewById(R.id.longitude_field)
        nameBankField = findViewById(R.id.name_bank_field)
        urlField = findViewById(R.id.url_field)
        phoneField = findViewById(R.id.phone_field)
        cityField = findViewById(R.id.city_field)
        prepaidField = findViewById(R.id.prepaid_field)
    }
}