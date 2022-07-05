package com.ahmetbozkan.bgnmobi.di

import android.content.Context
import com.ahmetbozkan.bgnmobi.BuildConfig
import com.ahmetbozkan.bgnmobi.data.remote.service.PokeApi
import com.ahmetbozkan.bgnmobi.data.remote.util.ErrorHandlerInterceptor
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
    private const val TIMEOUT: Long = 10

    @Provides
    @Singleton
    fun provideRetrofit(client: Lazy<OkHttpClient>): PokeApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory { client.get().newCall(it) }
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttp(
        cache: Cache,
        errorHandlerInterceptor: ErrorHandlerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(errorHandlerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    fun provideErrorHandlerInterceptor(@ApplicationContext context: Context): ErrorHandlerInterceptor =
        ErrorHandlerInterceptor(context)

    @Provides
    @Singleton
    fun provideOkHttpCache(@ApplicationContext context: Context): Cache = Cache(
        context.cacheDir,
        CLIENT_CACHE_SIZE
    )

}