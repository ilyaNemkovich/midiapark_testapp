package com.midiapark.newsapp.ui.screens.search.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.midiapark.newsapp.databinding.ItemSearchHistoryItemBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class SearchHistoryItem(item: String) :
    ModelAbstractBindingItem<String, ItemSearchHistoryItemBinding>(item) {

    override val type: Int = 227
    override var identifier = item.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemSearchHistoryItemBinding {
        return ItemSearchHistoryItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ItemSearchHistoryItemBinding, payloads: List<Any>) {
        binding.tvText.text = model
    }
}