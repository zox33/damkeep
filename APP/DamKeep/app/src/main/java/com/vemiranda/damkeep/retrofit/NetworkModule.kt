package com.vemiranda.damkeep.retrofit

import com.vemiranda.damkeep.common.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(damKeepTokenInterceptor: DamKeepInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(damKeepTokenInterceptor).addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            connectTimeout(Constants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }


    @Singleton
    @Provides
    fun provideDamKeepService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): IDamKeepService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(IDamKeepService::class.java)
    }

}