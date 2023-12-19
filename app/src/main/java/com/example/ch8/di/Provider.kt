package com.example.ch8.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.ch8.data.local.DataStoreManager
import com.example.ch8.data.remote.service.SpoonacularService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Deprecated(message = "replaced by library", level = DeprecationLevel.ERROR)
class Provider(
    val context: Application,
) {
    // val workManager = WorkManager.getInstance(context)

    private val chucker = ChuckerInterceptor(context)

    private val httpLogger =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(chucker)
//        .addInterceptor(httpLogger)
            .build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val tmdbService: SpoonacularService = retrofit.create(SpoonacularService::class.java)

//    private val tmdbService: TMDBService = FakeTMDBService()

    private val dataStoreManager = DataStoreManager(context)

//    val localRepository: LocalRepository = LocalRepository(
//        dataStoreManager = dataStoreManager,
//    )

//    val remoteRepository: RemoteRepository = RemoteRepository(
//        tmdbService = tmdbService,
//    )
}
