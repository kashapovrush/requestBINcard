package com.kashapovrush.cardbinrequest

import android.net.Uri
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException
import java.util.*

class NetworkUtil {


    companion object {

        val BINLIST_URL_BASE : String = "https://lookup.binlist.net/"
        lateinit var builtUri: Uri
        lateinit var urlConnection: HttpURLConnection

        fun generateURL(numberBIN: String?): URL? {
            builtUri = Uri.parse(BINLIST_URL_BASE + numberBIN)
            var url: URL? = null
            try {
                url = URL(builtUri.toString())
            }
            catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return url
        }

        fun getResponseFromURL(url: URL?): String {
            urlConnection = url?.openConnection() as HttpURLConnection
            try {
                var input: InputStream = urlConnection.inputStream
                var scanner: Scanner = Scanner(input)
                scanner.useDelimiter("\\A")
                var hasInput: Boolean = scanner.hasNext()
                if (hasInput) {
                    return scanner.next()
                } else {
                    return null!!
                }
            } catch (e: UnknownHostException) {
                return null!!
            }
            finally {
                urlConnection.disconnect()
            }
        }
    }


}