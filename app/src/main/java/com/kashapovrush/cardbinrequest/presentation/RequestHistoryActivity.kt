package com.kashapovrush.cardbinrequest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.cardbinrequest.databinding.ActivityRequestHistoryBinding
import javax.inject.Inject

class RequestHistoryActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRequestHistoryBinding
    private lateinit var adapter: CardInfoAdapter
    private lateinit var viewModel: RequestHistoryViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CardInfoApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityRequestHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[RequestHistoryViewModel::class.java]

        setRecyclerView()

        viewModel.cardList.observe(this) {
            adapter.submitList(it)
        }

        adapter.onCardInfoClickListener = {
            startActivity(CardInfoActivity.newIntent(this, it.id))
        }


    }

    private fun setRecyclerView() {
        val rvCardList = binding.rvCardList
        adapter = CardInfoAdapter()
        rvCardList.adapter = adapter

        setTouchHelper(rvCardList)
    }

    private fun setTouchHelper(rvCardList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCardInfo(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvCardList)
    }

    companion object{

        fun newIntent(context: Context): Intent {
            return Intent(context, RequestHistoryActivity::class.java)
        }
    }

}