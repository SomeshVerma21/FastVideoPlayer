package com.gamest.fastvideoplayer.di

import android.app.Application
import com.gamest.fastvideoplayer.data.repo.VideoMediaRepoImpl
import com.gamest.fastvideoplayer.domain.repo.VideoMediaRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideoMediaRepo(application: Application) : VideoMediaRepo{
        return VideoMediaRepoImpl(application)
    }
}