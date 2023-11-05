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
import com.kashapovrush.cardbinrequest.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferenceManager: PreferenceManager
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
                binding.resultLayout.visibility = View.VISIBLE

                binding.schemeField.text = schemeFieldText
                binding.typeField.text = typeFieldText
                binding.brandField.text = brandFieldText
                if (prepaidFieldText.equals("false")) {
                    binding.prepaidField.text = "NO"
                } else {
                    binding.prepaidField.text = "YES"
                }

                if (luhnFieldText.equals("false")) {
                    binding.luhnField.text = "NO"
                } else {
                    binding.luhnField.text = "YES"
                }

                binding.lengthField.text = lengthFieldText



                binding.numericField.text = numericFieldText
                binding.alphaField.text = alpha2FiledText
                binding.nameCountryField.text = nameCountryFieldText
                binding.emojiField.text = emojiFieldText
                binding.currencyField.text = currencyFieldText
                binding.latitudeField.text = latitudeFieldText
                binding.longitudeField.text = longitudeFieldText

                binding.nameBankField.text = nameBankFieldText
                binding.urlField.text = urlFieldText
                binding.phoneField.text = phoneFieldText
                binding.cityField.text = cityFieldText

            } else {
                binding.resultLayout.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager(applicationContext)
        val onClickListener = View.OnClickListener {
            val generatedURL: URL? = generateURL(binding.searchId.text.toString())
            BINQueryTask().execute(generatedURL)
            preferenceManager.putString("key", binding.searchId.text.toString())
        }
        binding.searchButton.setOnClickListener(onClickListener)
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

}