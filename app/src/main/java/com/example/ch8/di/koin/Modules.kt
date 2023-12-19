package com.example.ch8.di.koin

import androidx.work.WorkManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.ch8.data.local.DataStoreManager
import com.example.ch8.data.local.LocalRepository
import com.example.ch8.data.remote.RemoteRepository
import com.example.ch8.data.remote.service.SpoonacularService
import com.example.ch8.domain.repository.AccountRepository
import com.example.ch8.domain.repository.AuthRepository
import com.example.ch8.domain.repository.ImageRepository
import com.example.ch8.domain.repository.MovieRepository
import com.example.ch8.domain.usecase.AuthenticateUseCase
import com.example.ch8.presentation.auth.login.LoginViewModel
import com.example.ch8.presentation.auth.login.usecase.AuthenticateUseCaseImp
import com.example.ch8.presentation.auth.login.usecase.CheckLoginUseCase
import com.example.ch8.presentation.auth.login.usecase.SaveTokenUseCase
import com.example.ch8.presentation.auth.register.RegisterUseCase
import com.example.ch8.presentation.auth.register.RegisterViewModel
import com.example.ch8.presentation.blur.BlurViewModel
import com.example.ch8.presentation.home.HomeViewModel
import com.example.ch8.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

private val generalModule =
    module {
        single { ChuckerInterceptor(get()) }
        single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
        single { provideOkhttpClient(get(), get()) }
        single { "https://api.spoonacular.com/" }
        single { GsonConverterFactory.create() }
        single { provideRetrofit(get(), get(), get()) }
        single { provideTMDBService(get()) }
        single { DataStoreManager(get()) }
        single { WorkManager.getInstance(get()) }
        single <CoroutineContext>{Dispatchers.IO}

        // repository
        single<AccountRepository> {LocalRepository(get(), get())}
        single<AuthRepository> {LocalRepository(get(), get())}
        single<ImageRepository> {LocalRepository(get(), get())}

        // data
        single<MovieRepository> { RemoteRepository(get()) }

        // usecase
        single<AuthenticateUseCase>{AuthenticateUseCaseImp(get())}
        single {CheckLoginUseCase(get())}
        single {SaveTokenUseCase(get())}
        single {RegisterUseCase(get())}
    }

private val viewModelModule =
    module {
        viewModel { LoginViewModel(get(), get(), get(), get()) }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { ProfileViewModel(get(),get()) }
        viewModel { BlurViewModel(get(), get()) }
    }

val appModules = listOf(generalModule, viewModelModule)

private fun provideOkhttpClient(
    chuckerInterceptor: ChuckerInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

private fun provideTMDBService(retrofit: Retrofit): SpoonacularService {
    return retrofit.create(SpoonacularService::class.java)
}

private fun provideDispatcher(): CoroutineContext {
    return Dispatchers.IO
}
