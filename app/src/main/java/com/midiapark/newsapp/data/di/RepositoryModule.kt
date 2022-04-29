package com.midiapark.newsapp.data.di

import com.midiapark.newsapp.data.repository.NewsRepository
import com.midiapark.newsapp.data.repository.NewsRepositoryImpl
import com.midiapark.newsapp.domain.NewsInteractor
import com.midiapark.newsapp.domain.NewsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}