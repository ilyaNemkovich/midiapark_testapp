package com.midiapark.newsapp.ui.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.midiapark.newsapp.R
import com.midiapark.newsapp.ui.util.NavigationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationManager {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), "main_fragment")
                .commit()
        }
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment, fragment.javaClass.simpleName)
            addToBackStack(fragment.javaClass.simpleName)
        }
    }
}