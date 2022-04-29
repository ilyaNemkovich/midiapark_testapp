package com.midiapark.newsapp.domain.di

import com.midiapark.newsapp.domain.NewsInteractor
import com.midiapark.newsapp.domain.NewsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindNewsInteractor(
        newsInteractorImpl: NewsInteractorImpl
    ): NewsInteractor
}