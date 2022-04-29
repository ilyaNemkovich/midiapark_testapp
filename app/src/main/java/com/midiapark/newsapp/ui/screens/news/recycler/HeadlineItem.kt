package com.midiapark.newsapp.ui.screens.news.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.midiapark.newsapp.data.newtwork.dto.NewsResponse
import com.midiapark.newsapp.databinding.ItemHeadlineBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class HeadlineItem(item: NewsResponse) :
    ModelAbstractBindingItem<NewsResponse, ItemHeadlineBinding>(item) {

    override val type: Int = 229
    override var identifier = item.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemHeadlineBinding {
        return ItemHeadlineBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ItemHeadlineBinding, payloads: List<Any>) {
        binding.ivImage.load(model.image)
        binding.tvTitle.text = model.title
        binding.tvSubtitle.text = model.description
    }
}