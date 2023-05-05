package com.raywenderlich.rwnews.di

import com.raywenderlich.rwnews.logger.RwNewsLogger
import com.raywenderlich.rwnews.repository.NewsRepository
import com.raywenderlich.rwnews.repository.entity.News
import com.raywenderlich.rwnews.util.FakeNewsLogger
import com.raywenderlich.rwnews.util.FakeNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // 1
object TestAppModule {

    @Provides
    fun provideNewsRepository(): NewsRepository { // 2
        return FakeNewsRepository().apply {
            insert(News(1, "First Title", "First Body"))
            insert(News(2, "Second Title", "Second Body"))
            insert(News(3, "Third Title", "Third Body"))
        }
    }

    @Provides
    fun provideNewsLogger(): RwNewsLogger = FakeNewsLogger() // 2
}