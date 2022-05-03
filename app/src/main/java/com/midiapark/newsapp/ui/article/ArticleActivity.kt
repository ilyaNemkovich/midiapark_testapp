package com.midiapark.newsapp.ui.article

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.midiapark.newsapp.R
import com.midiapark.newsapp.data.newtwork.dto.NewsResponse
import com.midiapark.newsapp.entities.ArticleHeadline
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        intent.getParcelableExtra<ArticleHeadline>("article")?.apply { openArticle(this) }
    }

    private fun openArticle(article: ArticleHeadline) {
        val webView = findViewById<WebView>(R.id.wv_article)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(article.url)
    }

    companion object {
        fun newIntent(context: Context, article: ArticleHeadline): Intent {
            return Intent(context, ArticleActivity::class.java).apply {
                putExtra("article", article)
            }
        }
    }
}