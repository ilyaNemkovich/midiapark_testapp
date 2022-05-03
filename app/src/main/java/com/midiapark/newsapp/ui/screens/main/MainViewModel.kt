package com.midiapark.newsapp.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _tab = MutableLiveData("news")
    val tab: LiveData<String> = _tab

    fun setTab(tab: String) {
        _tab.value = tab
    }
}