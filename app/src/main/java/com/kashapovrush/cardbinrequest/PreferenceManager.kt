package com.kashapovrush.cardbinrequest

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager {
    var sharedPreferences: SharedPreferences? = null

    fun PreferenceManager(context: Context) {
        sharedPreferences = context.getSharedPreferences(,
            Context.MODE_PRIVATE
        )
    }
}