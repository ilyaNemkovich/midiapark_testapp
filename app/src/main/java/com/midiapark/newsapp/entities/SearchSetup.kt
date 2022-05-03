package com.midiapark.newsapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class SearchSetup(
    val searchIn: List<SearchIn>,
    val dateFrom: LocalDateTime,
    val dateTo: LocalDateTime,
    val order: ResultOrder
) : Parcelable {
    companion object {
        fun getDefault() = SearchSetup(
            listOf(SearchIn.TITLE, SearchIn.DESCRIPTION),
            LocalDateTime.now().minusDays(10),
            LocalDateTime.now(),
            ResultOrder.UPLOAD_DATE
        )
    }
}

@Parcelize
enum class SearchIn : Parcelable {
    TITLE, DESCRIPTION, CONTENT
}

@Parcelize
enum class ResultOrder : Parcelable {
    RELEVANCE, UPLOAD_DATE;
}

fun ResultOrder.toApiString(): String = when (this) {
    ResultOrder.RELEVANCE -> "relevance"
    ResultOrder.UPLOAD_DATE -> "publishedAt"
}