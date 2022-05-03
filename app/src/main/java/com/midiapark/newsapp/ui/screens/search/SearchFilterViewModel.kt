package com.midiapark.newsapp.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midiapark.newsapp.entities.SearchSetup
import dagger.hilt.android.lifecycle.HiltViewModel
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SearchFilterViewModel @Inject constructor(

) : ViewModel() {

    private val _searchSetup = MutableLiveData<SearchSetup>()
    val searchSetup: LiveData<SearchSetup> = _searchSetup

    fun getSearchSetup(): SearchSetup = requireNotNull(_searchSetup.value)

    fun setSearchSetup(searchSetup: SearchSetup) {
        _searchSetup.value = searchSetup
    }

    fun updateFromDate(date: LocalDateTime) {
        if (date.isAfter(_searchSetup.value!!.dateTo)) {
            _searchSetup.value =
                _searchSetup.value!!.copy(dateFrom = _searchSetup.value!!.dateTo, dateTo = date)
        } else {
            _searchSetup.value = _searchSetup.value!!.copy(dateFrom = date)
        }
    }

    fun updateToDate(date: LocalDateTime) {
        if (date.isBefore(_searchSetup.value!!.dateFrom)) {
            _searchSetup.value =
                _searchSetup.value!!.copy(dateFrom = date, dateTo = _searchSetup.value!!.dateFrom)
        } else {
            _searchSetup.value = _searchSetup.value!!.copy(dateTo = date)
        }
    }
}