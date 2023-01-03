package com.penguin.musicinfo.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.penguin.musicinfo.network.api.AppleMusicService
import com.penguin.musicinfo.network.api.ItunesAPIService
import com.penguin.musicinfo.network.repository.ItunesSearcherRepository
import com.penguin.musicinfo.network.repository.ItunesSearcherRepositoryImpl
import com.penguin.musicinfo.network.usecases.GetArtistInfoUseCase
import com.penguin.musicinfo.network.usecases.GetItunesLookupUseCase
import com.penguin.musicinfo.network.usecases.GetItunesMusicUseCase
import com.penguin.musicinfo.util.ResponseBodyConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIME_OUT = 40L
    private const val BASE_ITUNES_URL = "https://itunes.apple.com/"

    @Provides
    @Singleton
    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun createOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi by lazy {
            val moshiBuilder = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
            moshiBuilder.build()
        }


        return Retrofit.Builder()
            .baseUrl(BASE_ITUNES_URL)
            .client(okHttpClient)
            .addConverterFactory(ResponseBodyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun createItunesService(retrofit: Retrofit): ItunesAPIService {
        return retrofit.create(ItunesAPIService::class.java)
    }

    @Provides
    @Singleton
    fun createAppleMusicService(retrofit: Retrofit): AppleMusicService {
        return retrofit.create(AppleMusicService::class.java)
    }

    @Provides
    @Singleton
    fun createItunesRepository(
        itunesMusicSearch: GetItunesMusicUseCase,
        itunesArtistUseCase: GetItunesLookupUseCase
    ): ItunesSearcherRepository {
        return ItunesSearcherRepositoryImpl(itunesMusicSearch, itunesArtistUseCase)
    }

    @Provides
    @Singleton
    fun createGetResultsUseCase(api: ItunesAPIService): GetItunesMusicUseCase {
        return GetItunesMusicUseCase(api)
    }

    @Provides
    @Singleton
    fun createGetArtistUseCase(api: ItunesAPIService): GetItunesLookupUseCase {
        return GetItunesLookupUseCase(api)
    }

    @Provides
    @Singleton
    fun createGetArtistInfoUseCase(api: AppleMusicService): GetArtistInfoUseCase {
        return GetArtistInfoUseCase(api)
    }
}