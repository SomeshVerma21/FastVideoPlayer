package com.gamest.fastvideoplayer.di

import android.app.Application
import com.gamest.fastvideoplayer.data.remote.apis.SpotifyApi
import com.gamest.fastvideoplayer.data.repo.VideoMediaRepoImpl
import com.gamest.fastvideoplayer.domain.home.repo.VideoMediaRepo
import com.gamest.fastvideoplayer.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideoMediaRepo(application: Application) : VideoMediaRepo {
        return VideoMediaRepoImpl(application)
    }

    @Provides
    @Singleton
    fun getSpotifyApi() : SpotifyApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_SPOTIFY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyApi::class.java)
    }
}