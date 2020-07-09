package com.fromthebasement.githubrepos.di

import com.fromthebasement.githubrepos.Network.baseUrl
import com.fromthebasement.githubrepos.network.OkHttpInterceptor
import com.fromthebasement.githubrepos.network.api.GithubApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

/**
 * Module with dependencies needed for the network layer
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    /**
     * Api Interface for all requests regarding Github Repositories
     */
    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    /**
     * Retrofit using this application's Moshi and OkHttpClient builds
     */
    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Moshi with Json Adapter for Kotlin
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

    /**
     * OkHttpClient with custom [OkHttpInterceptor]
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(OkHttpInterceptor())
            .build()
    }

}