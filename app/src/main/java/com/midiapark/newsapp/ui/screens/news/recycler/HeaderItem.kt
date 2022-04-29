package com.midiapark.newsapp.ui.screens.news.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.midiapark.newsapp.databinding.ItemHeaderBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class HeaderItem(item: String) :
    ModelAbstractBindingItem<String, ItemHeaderBinding>(item) {

    override val type: Int = 228
    override var identifier = item.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemHeaderBinding {
        return ItemHeaderBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ItemHeaderBinding, payloads: List<Any>) {
        binding.tvText.text = model
    }
}