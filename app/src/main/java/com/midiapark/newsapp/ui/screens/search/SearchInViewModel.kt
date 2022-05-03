package com.midiapark.newsapp.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midiapark.newsapp.entities.SearchIn
import com.midiapark.newsapp.entities.SearchSetup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchInViewModel @Inject constructor(

) : ViewModel() {

    private val _searchSetup = MutableLiveData<SearchSetup>()
    val searchSetup: LiveData<SearchSetup> = _searchSetup

    fun getSearchSetup(): SearchSetup = requireNotNull(_searchSetup.value)

    fun setSearchSetup(searchSetup: SearchSetup) {
        _searchSetup.value = searchSetup
    }

    fun addOption(option: SearchIn) {
        val newList =
            requireNotNull(_searchSetup.value).searchIn.toMutableList()
                .apply { add(option) }.toSet()
        _searchSetup.value = _searchSetup.value!!.copy(searchIn = newList.toList())
    }

    fun removeOption(option: SearchIn) {
        val newList =
            requireNotNull(_searchSetup.value).searchIn.toMutableList().apply { remove(option) }
        _searchSetup.value = _searchSetup.value!!.copy(searchIn = newList)
    }
}