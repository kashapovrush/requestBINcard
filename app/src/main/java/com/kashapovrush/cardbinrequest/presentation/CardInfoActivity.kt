package com.kashapovrush.cardbinrequest.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kashapovrush.cardbinrequest.R

class CardInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)
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