package com.kashapovrush.cardbinrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashapovrush.cardbinrequest.databinding.ActivityRequestHistoryBinding

class RequestHistory : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var adapter: ListHistoryAdapter
    private lateinit var binding: ActivityRequestHistoryBinding
    private lateinit var listHistory: ListHistory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager(applicationContext)

        initRV()

        var BINNumber: String? = preferenceManager.getString("key")

        listHistory = ListHistory()
        listHistory.listItem = BINNumber
        var listArray = mutableListOf<ListHistory?>()
        listArray.add(listHistory)
        adapter.submitList(listArray)

    }


    private fun initRV() = with(binding) {
        adapter = ListHistoryAdapter()
        rvList.layoutManager = LinearLayoutManager(this@RequestHistory)
        rvList.adapter = adapter
    }

}

